package br.com.banco.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.banco.domain.model.Conta;

@Component
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
