package com.algaworks.algafood.domain.exception;

// Nao e' mais necessaria a anotacao abaixo, pois com a classe "ApiExceptionHandler", centralizamos
// o tratamento de excecoes e os coidgos HTTP que cada uma deve devolver na reposta
// @ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}