package br.com.banco.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.api.model.ContaDTO;
import br.com.banco.domain.exception.ContaNaoEncontradaException;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.repository.TransferenciaRepository;
import br.com.banco.domain.service.CadastroContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

  @Autowired
  private CadastroContaService cadastroConta;

  @Autowired
  private TransferenciaRepository transferenciaRepository;

  @GetMapping("/{id}")
  public ResponseEntity<?> buscar(@PathVariable Long id) { 
    try {  
      Conta conta = cadastroConta.buscarPorId(id);
      
      return ResponseEntity.ok().body(convertToDTO(conta));
    } catch (ContaNaoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/transferencia-por-nome-e-data")
  public List<Transferencia> transferenciaNemeroConta(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim, String nome) {

    return transferenciaRepository.findByNomeData(dataInicio, dataFim, nome);
  }

  private ContaDTO convertToDTO(Conta conta) {
    ContaDTO contaDTO = new ContaDTO();
    contaDTO.setId(conta.getId());
    contaDTO.setNome(conta.getNome());
    contaDTO.setTransferencias(conta.getTransferencias());
    return contaDTO;
  }
}
