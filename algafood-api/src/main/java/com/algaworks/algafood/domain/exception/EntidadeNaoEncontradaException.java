package com.algaworks.algafood.domain.exception;

// Nao e' mais necessaria a anotacao abaixo, pois com a classe "ApiExceptionHandler", centralizamos
// o tratamento de excecoes e os coidgos HTTP que cada uma deve devolver na reposta
// @ResponseStatus(HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}