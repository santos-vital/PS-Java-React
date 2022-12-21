package br.com.banco.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.banco.domain.model.Transferencia;

public interface TransferenciaRepositoryQueries {

  List<Transferencia> findByNomeData(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
      String nome);
}
