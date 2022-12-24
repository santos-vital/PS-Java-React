package br.com.banco.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.dto.ContaDTO;
import br.com.banco.domain.repository.ContaRepository;
import br.com.banco.domain.service.CadastroContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

  @Autowired
  private ContaRepository contaRepository;

  @Autowired
  private CadastroContaService cadastroConta;

  @GetMapping
  public List<Conta> listar() {
    return contaRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Conta> buscar(@PathVariable Long id) {
    Optional<Conta> conta = contaRepository.findById(id);

    if (conta.isPresent()) {
      return ResponseEntity.ok(conta.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public Conta adicionar(@RequestBody @Valid ContaDTO contaDTO) {

    return cadastroConta.salvar(new Conta(contaDTO));
  }
}
