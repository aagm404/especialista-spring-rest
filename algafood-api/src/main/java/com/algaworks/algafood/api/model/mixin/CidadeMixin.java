package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {
	
	@JsonIgnoreProperties(value = {"nome", "sigla"}, allowGetters = true)
	private Estado estado;
}