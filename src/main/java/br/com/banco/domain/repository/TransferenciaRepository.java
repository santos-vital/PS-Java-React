package br.com.banco.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.com.banco.domain.model.Transferencia;

@Component
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>, TransferenciaRepositoryQueries {

        List<Transferencia> findByDataTransferenciaBetween(
                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);

        List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);
}
