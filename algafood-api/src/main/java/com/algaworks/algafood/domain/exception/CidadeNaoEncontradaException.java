package com.algaworks.algafood.domain.exception;

// Nao e' necessario a anotacao abaixo, pois esta exception vai devolver o mesmo HttpStatus da excecao que ela herda
// Logo, a excecao EntidadeNaoEncontradaException vai devolver o status HTTP que queremos
// @ResponseStatus(HttpStatus.NOT_FOUND)
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long id) {
		this(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
	}
}