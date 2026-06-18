package com.projetoLoginIzabele.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoLoginIzabele.entity.Produtos;
import com.projetoLoginIzabele.repository.ProdutosRepository;

@Service
public class ProdutosService {

	private final ProdutosRepository produtosRepository;

	@Autowired
	public ProdutosService(ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}

	public List<Produtos> buscaTodosProdutos() {
		return produtosRepository.findAll();
	}

	public Produtos buscaProdutosId(Long id) {
		Optional<Produtos> Produtos = produtosRepository.findById(id);
		return Produtos.orElse(null);
	}

	public Produtos salvaProdutos(Produtos produto) {
		return produtosRepository.save(produto);
	}

	public Produtos alterarProdutos(Long id, Produtos alterarP) {
		Optional<Produtos> existeProdutos = produtosRepository.findById(id);
		if (existeProdutos.isPresent()) {
			alterarP.setId(id);
			return produtosRepository.save(alterarP);
		}
		return null;
	}

	public boolean apagarProdutos(Long id) {
		Optional<Produtos> existeProdutos = produtosRepository.findById(id);
		if (existeProdutos.isPresent()) {
			produtosRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
