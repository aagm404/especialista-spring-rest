package com.algaworks.algafood.domain.exception;

// Nao e' necessario a anotacao abaixo, pois esta exception vai devolver o mesmo HttpStatus da excecao que ela herda
// Logo, a excecao EntidadeNaoEncontradaException vai devolver o status HTTP que queremos
// @ResponseStatus(HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long id) {
		this(String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
	}
}