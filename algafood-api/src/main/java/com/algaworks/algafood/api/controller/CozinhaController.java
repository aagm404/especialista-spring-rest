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

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK) // Deixamos esta linha por fins didaticos. O padrao ja e o codigo 200(OK)
	public List<Cozinha> listar() {
		return cadastroCozinhaService.listar();
		
//		List<Cozinha> cozinhas = cadastroCozinhaService.listar();
//		return ResponseEntity.ok(cozinhas);
	}

	@GetMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.OK) // Deixamos esta linha por fins didaticos. O padrao ja e o codigo 200(OK)
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {

		return cadastroCozinhaService.buscar(id);
		
//		try {
//
//			Cozinha cozinha = cadastroCozinhaService.buscar(id);
//			return ResponseEntity.ok(cozinha);
//
//		} catch (EntidadeNaoEncontradaException e) {
//
////		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			return ResponseEntity.notFound().build();
//		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody @Valid Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
		
//		Cozinha cozinhaPersistida = cadastroCozinhaService.salvar(cozinha);
//		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaPersistida);
	}

	@PutMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.OK) // Deixamos esta linha por fins didaticos. O padrao ja e o codigo 200(OK)
	public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid Cozinha cozinha) {
		Cozinha cozinhaPersistida = cadastroCozinhaService.buscar(cozinhaId);
		BeanUtils.copyProperties(cozinha, cozinhaPersistida, "id");
		return cadastroCozinhaService.salvar(cozinhaPersistida);

//		try {
//			Cozinha cozinhaArmazenada = cadastroCozinhaService.buscar(cozinhaId);
//			BeanUtils.copyProperties(cozinha, cozinhaArmazenada, "id");
//			cozinhaArmazenada = cadastroCozinhaService.salvar(cozinhaArmazenada);
//
//			return ResponseEntity.ok(cozinhaArmazenada);
//
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		}
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.remover(cozinhaId);
	}
}