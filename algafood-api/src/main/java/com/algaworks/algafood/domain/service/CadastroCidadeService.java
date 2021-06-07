package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removido, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscar(Long cidadeId) {
		// Abaixo, temos duas formas de implementar este metodo
		// Deixei uma comentada, mas tanto uma quanto outra estao corretas

//		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
//
//		if (cidade.isEmpty()) {
//			throw new CidadeNaoEncontradaException(cidadeId);
//		}
//
//		return cidade.get();

		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscar(estadoId);
		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	@Transactional
	public void remover(Long cidadeId) {
		try {

			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {

			throw new CidadeNaoEncontradaException(cidadeId);

		} catch (DataIntegrityViolationException e) {
			/*
			 * A excecao tratada acima no catch e' uma excecao que pode ser lancanda quando
			 * se tenta remover uma entrada que esta sendo usada por outra tabela atraves de
			 * um chave estrangeira (FK)
			 */
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
}