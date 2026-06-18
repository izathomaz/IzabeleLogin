package com.projetoLoginIzabele.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoLoginIzabele.entity.Estoque;
import com.projetoLoginIzabele.repository.EstoqueRepository;

@Service
public class EstoqueService {

	private final EstoqueRepository estoqueRepository;

	@Autowired
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}

	public List<Estoque> buscaTodosEstoques() {
		return estoqueRepository.findAll();
	}

	public Estoque buscaEstoqueId(Long id) {
		Optional<Estoque> Estoque = estoqueRepository.findById(id);
		return Estoque.orElse(null);
	}

	public Estoque salvaEstoque(Estoque estoque) {
		return estoqueRepository.save(estoque);
	}

	public Estoque alterarEstoque(Long id, Estoque alterarE) {
		Optional<Estoque> existeEstoque = estoqueRepository.findById(id);
		if (existeEstoque.isPresent()) {
			alterarE.setId(id);
			return estoqueRepository.save(alterarE);
		}
		return null;
	}

	public boolean apagarEstoque(Long id) {
		Optional<Estoque> existeEstoque = estoqueRepository.findById(id);
		if (existeEstoque.isPresent()) {
			estoqueRepository.deleteById(id);
			return true;
		}
		return false;
	}

}