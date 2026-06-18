package com.projetoLoginIzabele.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoLoginIzabele.entity.Produtos;
import com.projetoLoginIzabele.service.ProdutosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	private final ProdutosService produtosService;

	@Autowired
	public ProdutosController(ProdutosService produtosService) {
		this.produtosService = produtosService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produtos> buscaProdutosControlId(@PathVariable Long id) {
		Produtos produtos = produtosService.buscaProdutosId(id);
		if (produtos != null) {
			return ResponseEntity.ok(produtos);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/")
	public ResponseEntity<List<Produtos>> buscaProdutosControl() {
		List<Produtos> Produtos = produtosService.buscaTodosProdutos();
		return ResponseEntity.ok(Produtos);
	}

	@PostMapping("/")
	public ResponseEntity<Produtos> salvaProdutosControl(@RequestBody @Valid Produtos produtos) {
		Produtos salvaProdutos = produtosService.salvaProdutos(produtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProdutos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produtos> alteraProdutosControl(@PathVariable Long id, @RequestBody @Valid Produtos produtos) {
		Produtos alteraProdutos = produtosService.alterarProdutos(id, produtos);
		if (alteraProdutos != null) {
			return ResponseEntity.ok(produtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produtos> apagaProdutosControl(@PathVariable Long id) {
		boolean apagar = produtosService.apagarProdutos(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}