package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	/*
	 * As quatro consultas abaixo (uma em usa e tres comentadas) geram o mesm script SQL
	 */
	List<Cozinha> nome(String nome);
	
	List<Cozinha> nomeContaining(String nome);
	
	Boolean existsByNome(String nome);
	
//	List<Cozinha> findByNome(String nome);
//	List<Cozinha> findTodasByNome(String nome);
//	List<Cozinha> findTQualquerCoisaByNome(String nome);
	
	/*
	 * Veja que todos os metodos acima retornam uma lista de de Cozinha.
	 * Para fazer retornar apenas uma Cozinha, podemos assinar os metodos da mesma forma que acima,
	 * mudando, apenas o retorno de List<Cozinha> para Cozinha.
	 * 
	 * A unica diferenca e' que neste caso, temos que assegurar que a consulta retorne apens um resultado
	 */
//	Cozinha nome(String nome);
//	Cozinha findByNome(String nome);
//	Cozinha findTodasByNome(String nome);
//	Cozinha findTQualquerCoisaByNome(String nome);
	
	/*
	 * Pedemos retornar um Optional de Cozinha, com os mesmos metodos
	 */
//	Optional<Cozinha> nome(String nome);
//	Optional<Cozinha> findByNome(String nome);
//	Optional<Cozinha> findTodasByNome(String nome);
//	Optional<Cozinha> findTQualquerCoisaByNome(String nome);
}