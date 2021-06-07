package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	// Nao utilizamos a anotacao abaixo, pois ela ja e a opcao padrao, ou seja, sem nada explicito, e' retornado o codigo 200(OK)
	// @ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Estado> listar() {
		return cadastroEstadoService.listar();
		
//		List<Estado> estado = cadastroEstadoService.listar();
//		return ResponseEntity.ok(estado);
	}

	// Nao utilizamos a anotacao abaixo, pois ela ja e a opcao padrao, ou seja, sem nada explicito, e' retornado o codigo 200(OK)
	// @ResponseStatus(HttpStatus.OK)
	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable("estadoId") Long id) {

		return cadastroEstadoService.buscar(id);
//		try {
//
//			Estado estado = cadastroEstadoService.buscar(id);
//			return ResponseEntity.ok(estado);
//
//		} catch (EntidadeNaoEncontradaException e) {
//
////			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			return ResponseEntity.notFound().build();
//		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody @Valid Estado estado) {
		return cadastroEstadoService.salvar(estado);
//		Estado estadoPersistido = cadastroEstadoService.salvar(estado);
//		return ResponseEntity.status(HttpStatus.CREATED).body(estadoPersistido);
	}

	// Nao utilizamos a anotacao abaixo, pois ela ja e a opcao padrao, ou seja, sem nada explicito, e' retornado o codigo 200(OK)
	// @ResponseStatus(HttpStatus.OK)
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable Long estadoId, @RequestBody @Valid Estado estado) {
		Estado estadoPersistido = cadastroEstadoService.buscar(estadoId);
		BeanUtils.copyProperties(estado, estadoPersistido, "id");
		return cadastroEstadoService.salvar(estadoPersistido);
		
//		try {
//			Estado estadoArmazenado = cadastroEstadoService.buscar(estadoId);
//
//			BeanUtils.copyProperties(estado, estadoArmazenado, "id");
//			estadoArmazenado = cadastroEstadoService.salvar(estadoArmazenado);
//
//			return ResponseEntity.ok(estadoArmazenado);
//
//		} catch (EntidadeNaoEncontradaException e) {
//
//			return ResponseEntity.notFound().build();
//		}
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstadoService.remover(estadoId);
		
//		try {
//			cadastroEstadoService.remover(estadoId);
//
////		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//			return ResponseEntity.noContent().build();
//
//		} catch (EntidadeNaoEncontradaException e) {
//
//			return ResponseEntity.notFound().build();
//
//		} catch (EntidadeEmUsoException e) {
//
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
	}
}