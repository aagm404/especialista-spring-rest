package com.algaworks.algafood.domain.exception;

// Nao e' necessario a anotacao abaixo, pois esta exception vai devolver o mesmo HttpStatus da excecao que ela herda
// Logo, a excecao EntidadeNaoEncontradaException vai devolver o status HTTP que queremos
// @ResponseStatus(HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
	}
}