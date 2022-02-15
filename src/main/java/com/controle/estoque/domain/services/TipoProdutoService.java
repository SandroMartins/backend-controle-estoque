package com.controle.estoque.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.domain.repositories.TipoProdutoRepository;

@Service
public class TipoProdutoService {
	
	@Autowired
	private TipoProdutoRepository repository;

	public TipoProduto findById(Long id) {
		Optional<TipoProduto> obj = repository.findById(id);

		return obj.orElse(null);
	}
}
