package br.com.banco.infra.repository.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.repository.TransferenciaRepository;
import br.com.banco.domain.repository.TransferenciaRepositoryQueries;

@Repository
public class TransferenciaRepositoryImpl implements TransferenciaRepositoryQueries {

  @PersistenceContext
  private EntityManager manager;

  @Autowired
  @Lazy
  private TransferenciaRepository transferenciaRepository;

  @Override
  public List<Transferencia> findByNomeData(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
      String nome) {

    var builder = manager.getCriteriaBuilder();

    var criteria = builder.createQuery(Transferencia.class);
    var root = criteria.from(Transferencia.class);

    var predicates = new ArrayList<Predicate>();

    if (StringUtils.hasText(nome)) {
      predicates.add(builder.like(root.get("nomeOperadorTransacao"), "%" + nome + "%"));
    }

    if (dataInicio != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get("dataTransferencia"), dataInicio));
    }

    if (dataFim != null) {
      predicates.add(builder.lessThanOrEqualTo(root.get("dataTransferencia"), dataFim));
    }

    criteria.where(predicates.toArray(new Predicate[0]));

    var query = manager.createQuery(criteria);

    return query.getResultList();
  }
}
