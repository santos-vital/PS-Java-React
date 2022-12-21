package br.com.banco.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.domain.exception.EntidadeEmUsoException;
import br.com.banco.domain.exception.EntidadeNaoEncontradaException;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.repository.ContaRepository;
import br.com.banco.domain.repository.TransferenciaRepository;

@Service
public class CadastroTransferencia {

  @Autowired
  private TransferenciaRepository transferenciaRepository;

  @Autowired
  private ContaRepository contaRepository;

  public Transferencia salvar(Transferencia transferencia) {
    Long contaId = transferencia.getConta().getId();

    Conta conta = contaRepository.findById(contaId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format("Não foi possível encontrar uma transferencia com código %d", contaId)));

    transferencia.setConta(conta);

    return transferenciaRepository.save(transferencia);
  }

  public void excluir(Long id) {
    try {
      transferenciaRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(
          String.format("Não existe um cadastro de transferencia com código %d", id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Transferencia de código %d não pode ser removida, pois está em uso", id));
    }
  }
}
