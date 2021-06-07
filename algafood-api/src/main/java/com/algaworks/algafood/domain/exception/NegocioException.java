package com.algaworks.algafood.domain.exception;

/*
 * Classe de excecao para erros de negocio genericos. Nao especificos
 */

// Nao e' mais necessaria a anotacao abaixo, pois com a classe "ApiExceptionHandler", centralizamos
// o tratamento de excecoes e os coidgos HTTP que cada uma deve devolver na reposta
// @ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}