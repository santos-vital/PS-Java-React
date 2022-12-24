package br.com.banco.api.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

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
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Tipo;
import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.model.dto.TransferenciaDTO;

import br.com.banco.domain.repository.TransferenciaRepository;
import br.com.banco.domain.service.CadastroContaService;
import br.com.banco.domain.service.CadastroTransferencia;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

  @Autowired
  private TransferenciaRepository transferenciaRepository;

  @Autowired
  private CadastroTransferencia cadastroTransferencia;

  @Autowired
  private CadastroContaService cadastroContaService;

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

  @GetMapping("/nome-operador-transacao")
  public List<Transferencia> transferenciaPorNome(String nome) {
    return transferenciaRepository.findByNomeOperadorTransacao(nome);
  }

  @GetMapping("/transferencia-por-nome-e-data")
  public List<Transferencia> transferenciaPorDataNome(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim, String nome) {

    return transferenciaRepository.findByNomeData(dataInicio, dataFim, nome);
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {
    try {
      Transferencia transferencia = converter(transferenciaDTO);
      transferencia = cadastroTransferencia.salvar(transferencia);
      return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  private Transferencia converter(TransferenciaDTO transferenciaDTO) {
    Transferencia transferencia = new Transferencia();
    transferencia.setId(transferenciaDTO.getId());
    transferencia.setDataTransferencia(transferenciaDTO.getDataTransferencia());
    transferencia.setValor(transferenciaDTO.getValor());
    transferencia.setTipo(Tipo.valueOf(transferenciaDTO.getTipo()));
    transferencia.setNomeOperadorTransacao(transferenciaDTO.getNomeOperadorTransacao());
    Conta conta = cadastroContaService.buscarPorId(transferenciaDTO.getConta())
                            .orElseThrow( () -> new EntidadeNaoEncontradaException("Conta não encontrada"));
    transferencia.setConta(conta);

    return transferencia;
  }
}
