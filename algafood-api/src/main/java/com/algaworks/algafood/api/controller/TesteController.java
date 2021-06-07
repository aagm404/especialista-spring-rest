package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	// Abaixo, queremos que o cliente nos passe o nome da cozinha por parametro. 
	// O nome deste parametro e' o nome da variavel que o metodo recebe por parametro.
	// Nesse casso, o nome do parametro e' "nomeCozinha".
	
	@GetMapping("/cozinhas/por-nome")
	public ResponseEntity<List<Cozinha>> consultarPorNome(String nomeCozinha) {
		List<Cozinha> cozinhas = cozinhaRepository.nome(nomeCozinha);
		return ResponseEntity.ok(cozinhas);
	}
	
	@GetMapping("/cozinhas/contem-nome")
	public ResponseEntity<List<Cozinha>> consultarContendoNome(String nomeCozinha) {
		List<Cozinha> cozinhas = cozinhaRepository.nomeContaining(nomeCozinha);
		return ResponseEntity.ok(cozinhas);
	}
	
	@GetMapping("/cozinhas/exists")
	public Boolean existsNome(String nomeCozinha) {
		return cozinhaRepository.existsByNome(nomeCozinha);
	}
	
	@GetMapping("/restaurantes/taxa-frete-maior-que")
	public ResponseEntity<List<Restaurante>> consultarTaxaFreteMaiorQue(BigDecimal taxaFrete) {
		List<Restaurante> restaurantes = restauranteRepository.findByTaxaFreteGreaterThan(taxaFrete);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/intervalo-taxa-frete")
	public ResponseEntity<List<Restaurante>> consultarIntervaloTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		List<Restaurante> restaurantes = restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/por-nome-e-cozinha")
	public ResponseEntity<List<Restaurante>> consultarPorNomeECozinha(String nomeRestaurante, Long cozinhaId) {
		List<Restaurante> restaurantes = restauranteRepository.findByNomeContainingAndCozinhaId(nomeRestaurante, cozinhaId);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/por-nome-e-cozinha-query")
	public ResponseEntity<List<Restaurante>> consultarPorNomeECozinhaQuery(String nomeRestaurante, Long cozinhaId) {
		List<Restaurante> restaurantes = restauranteRepository.consultarPorNomeECozinhaQuery(nomeRestaurante, cozinhaId);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public ResponseEntity<List<Restaurante>> consultarPorNomeEFrete(String nomeRestaurante,  BigDecimal freteInical, BigDecimal freteFinal) {
		List<Restaurante> restaurantes = restauranteRepository.find(nomeRestaurante, freteInical, freteFinal);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public ResponseEntity<List<Restaurante>> restaurantesComFreteGratis(String nomeRestaurante) {
		List<Restaurante> restaurantes = restauranteRepository.findComFreteGratis(nomeRestaurante);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("restaurantes/top5")
	public ResponseEntity<List<Restaurante>> consultarTopTresPorCozinhaNomeLike (String cozinhaNome) {
		List<Restaurante> restaurantes = restauranteRepository.getABCTop5DEFByCozinhaNomeContaining(cozinhaNome);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public Integer restauranteCountPorCozinha(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}
	
	@GetMapping("/cozinhas/primeira")
	public Optional<Cozinha> cozinhaPrimeira() {
		return cozinhaRepository.buscarPrimeiro();
	}
}
