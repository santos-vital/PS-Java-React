package br.com.banco.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.domain.exception.ContaNaoEncontradaException;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.repository.ContaRepository;

@Service
public class CadastroContaService {

  @Autowired
  private ContaRepository contaRepository;

	public Conta buscarPorId(Long contaid) {
    return contaRepository.findById(contaid)
                          .orElseThrow( () -> new ContaNaoEncontradaException(contaid));
  }
}
