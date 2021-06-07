package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Estado buscar(Long estadoId) {

		// Abaixo, temos duas formas de implementar este metodo
		// Deixei uma comentada, mas tanto uma quanto outra estao corretas

//		Optional<Estado> estado = estadoRepository.findById(estadoId);
//
//		if (estado.isEmpty()) {
//			throw new EstadoNaoEncontradoException(estadoId);
//		}
//
//		return estado.get();

		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
		
	}

	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Transactional
	public void remover(Long estadoId) {
		try {

			estadoRepository.deleteById(estadoId);

		} catch (EmptyResultDataAccessException e) {

			throw new EstadoNaoEncontradoException(estadoId);

		} catch (DataIntegrityViolationException e) {
			/*
			 * A excecao tratada acima no catch e' uma excecao que pode ser lancanda quando
			 * se tenta remover uma entrada que esta sendo usada por outra tabela atraves de
			 * um chave estrangeira (FK)
			 */
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
}