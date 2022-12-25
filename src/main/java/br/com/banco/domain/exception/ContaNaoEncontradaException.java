package br.com.banco.domain.exception;

public class ContaNaoEncontradaException extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  public ContaNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public ContaNaoEncontradaException(Long contaId) {
		this(String.format("Não existe um cadastro de conta com código %d", contaId));
	}
}
