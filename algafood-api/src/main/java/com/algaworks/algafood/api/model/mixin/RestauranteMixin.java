package com.algaworks.algafood.api.model.mixin;

import java.time.LocalDateTime;
import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Na classe de Mixin, mantemos somente as anotacoes do Jackson
 * E na classe de modelo de dominio, mantemos todas as
 * antoacoes, exceto as anotacoes do Jackson
 */
public abstract class RestauranteMixin {

	// Ignorar o atributo "nome", do atributo cozinha, somente na desserializacao (JSON para objeto) de um restaurante
	// Assim, a aplicacao nao aceita requisicoes que contenham o nome de uma cozinha, mas se buscarmos por restaurantes,
	// ele retorna o nome da cozinha. Aceita o metodo getNome. Aceita serializacao (Objeto para JSON)
	@JsonIgnoreProperties(value = "nome", allowGetters = true) 
	private Cozinha cozinha;
	
	@JsonIgnore
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento;
	
	@JsonIgnore
	private List<Produto> produtos;
}
