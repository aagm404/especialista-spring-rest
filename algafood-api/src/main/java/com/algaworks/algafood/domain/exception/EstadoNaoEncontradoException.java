package com.algaworks.algafood.domain.exception;

// Nao e' necessario a anotacao abaixo, pois esta exception vai devolver o mesmo HttpStatus da excecao que ela herda
// Logo, a excecao EntidadeNaoEncontradaException vai devolver o status HTTP que queremos
// @ResponseStatus(HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de estado com código %d";

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long id) {
		this(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
	}
}