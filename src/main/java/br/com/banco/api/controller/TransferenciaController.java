package br.com.banco.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.domain.exception.EntidadeNaoEncontradaException;
import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.repository.TransferenciaRepository;
import br.com.banco.domain.service.CadastroTransferencia;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

  @Autowired
  private TransferenciaRepository transferenciaRepository;

  @Autowired
  private CadastroTransferencia cadastroTransferencia;

  @GetMapping
  public List<Transferencia> listar() {
    return transferenciaRepository.findAll();
  }

  @GetMapping("/transferencias-por-data")
  public List<Transferencia> transferenciasPorData(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

    return transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Transferencia transferencia) {
    try {
      transferencia = cadastroTransferencia.salvar(transferencia);

      return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
