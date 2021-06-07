package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

/**
 * Essa classe contem testes de integracao<br>
 * <br>
 * O professor implementou esta classe para exemplificar
 * os testes de integracao, mas depois apagou a classe<br>
 * <br>
 * O foco deste modulo sao os testes de API<br>
 * <br>
 * Eu mantive esta classe de testes de integracao, para manter
 * um registro dos estudos deste modulo<br>
 * <br>
 * Implementamos os codigos de API na classe "CadastroCozinhaIT"<br>
 * <br>
 * @author Antonio
 */

@SpringBootTest
@TestPropertySource("/application-test.properties")
class IntegracaoCadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		// 1 - cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Cozinha Cadastro Teste");

		// 2 - acao
		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		// 3 - validacao
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		// 1 - cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		// 2 - acao e 3 - validacao
		assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinha.salvar(novaCozinha);
		});
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		// 1- Cenario 2 - acao e 3 - validacao
		assertThrows(EntidadeEmUsoException.class, () -> {
			// Para garantir o cenario de teste, criamos uma cozinha,
			// um restaurante com esta cozinha e persistimos ambos.
			// Apos estas acoes, tentamos remover a cozinha
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome("Cozinha Em Uso Teste");
			novaCozinha = cadastroCozinha.salvar(novaCozinha);

			Restaurante restaurante = new Restaurante();
			restaurante.setNome("Restaurante Cozinha Em Uso Teste");
			restaurante.setTaxaFrete(new BigDecimal(15.00));
			restaurante.setCozinha(novaCozinha);
			cadastroRestaurante.salvar(restaurante);

			cadastroCozinha.remover(novaCozinha.getId());
		});
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		// 1- Cenario 2 - acao e 3 - validacao
		assertThrows(CozinhaNaoEncontradaException.class, () -> {
			// Para garantir o cenario de teste, criamos uma cozinha,
			// e a persistimos. Excluimos uma vez pelo Id, e tentamos 
			// excluir mais uma vez para simular a situacao
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome("Cozinha Exclusao Teste");
			novaCozinha = cadastroCozinha.salvar(novaCozinha);

			cadastroCozinha.remover(novaCozinha.getId());
			
			// Removendo novamente a mesma cozinha, para garantir o cenario de teste
			cadastroCozinha.remover(novaCozinha.getId());
		});
	}
}