package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Nesta classe implementamos os testes de API<br>
 * <br>
 * Testes de API devem, de fato, fazer uma chamada
 * HTTP real à API e validar a resposta da API<br>
 * <br>
 * Existem varias bibliotecas, no mundo Java, para se
 * chamar uma API via HTTP<br>
 * <br>
 * Neste curso, utilizamos a biblioteca "REST-assured"<br>
 * <br>
 * Esta biblioteca nao vem nativamente no projeto Spring.
 * Precisamos adicionar no pom.xml<br>
 * <br>
 * @author Antonio
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	private static final int ID_COZINHA_INEXISTENTE = 100;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner dbCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaJaponesa;
	private Cozinha cozinhaBrasileira;
	
	// Este metodo de callback, com a anotacao @BeforeEach, abaixo
	// E' executado uma vez, imediatamente antes, de cada metodo
	// que contem a anotacao @Test. Ou seja, sera' executado tantas
	// vezes quanto o numero de testes existentes
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = this.port;
		RestAssured.basePath="/cozinhas";
		
		jsonCorretoCozinhaJaponesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-japonesa.json");
		
		// A linha abaixo e' para limpar a base de dados de teste, antes de cada teste
		dbCleaner.clearTables();
		
		// A linha abaixo e' para popular a base de dados de teste, antes de cada teste
		prepararDados();
	}
	
	private void prepararDados() {
		cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileira);
				
		Cozinha cozinhaInglesa = new Cozinha();
		cozinhaInglesa.setNome("Inglesa");
		cozinhaRepository.save(cozinhaInglesa);
		
		Cozinha cozinhaChilena = new Cozinha();
		cozinhaChilena.setNome("Chilena");
		cozinhaRepository.save(cozinhaChilena);
		
		Cozinha cozinhaCanadense = new Cozinha();
		cozinhaCanadense.setNome("Canadense");
		cozinhaRepository.save(cozinhaCanadense);
		
		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(quantidadeCozinhasCadastradas))
			.body("nome", hasItems("Brasileira", "Canadense"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(jsonCorretoCozinhaJaponesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhaBrasileira.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaBrasileira.getNome()));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoConsultarCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}