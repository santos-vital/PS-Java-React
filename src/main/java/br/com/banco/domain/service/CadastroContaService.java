package br.com.banco.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.domain.exception.EntidadeEmUsoException;
import br.com.banco.domain.exception.EntidadeNaoEncontradaException;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.repository.ContaRepository;

@Service
public class CadastroContaService {

  @Autowired
  private ContaRepository contaRepository;

  public Conta salvar(Conta conta) {

    return contaRepository.save(conta);
  }

  public void excluir(Long id) {
    try {
      contaRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de conta com código %d", id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(String.format("Conta de código %d não pode ser removida, pois está em uso", id));
    }
  }
}
