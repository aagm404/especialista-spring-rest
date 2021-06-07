package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
//	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteGreaterThan (BigDecimal taxaFrete);
	
	List<Restaurante> findByTaxaFreteBetween (BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	// Abaixo comentamos a anotacao @Query para podermos utilizar a externalização de querys
	// no arquivo "src/main/resources/META-INF/orm.xml
	// Deixamos anotado abaixo para fins didaticos
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNomeECozinhaQuery(String nome, @Param("id") Long cozinha);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	// O metodo abaixo funciona perfeitamente:
	// Temos o prefixo "get...By..."
	// A keyword "Top<number>"
	// Qualquer coisa escrita entre "get...By" para descrever o metodo
	// O nome do atributo
	// Sufixo "Containing"
	List<Restaurante> getABCTop5DEFByCozinhaNomeContaining(String nome);
	
	Integer countByCozinhaId(Long id);
}