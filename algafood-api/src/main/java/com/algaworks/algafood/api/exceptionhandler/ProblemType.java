package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_DE_SISTEMA("Erro de sistema", "/erro-de-sistema"),
	ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem Incompreensível","/mensagem-incompreensivel"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado");
	
	private String title;
	private String uri;
	
	ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://algafood.com.br" + path;
	}
}