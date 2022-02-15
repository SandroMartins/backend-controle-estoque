package com.controle.estoque.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.controle.estoque.domain.Produto;
import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.domain.repositories.ProdutoRepository;
import com.controle.estoque.domain.repositories.TipoProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);

		return obj.orElse(null);
	}
	
	public Page<Produto> findDistinctByTipoProduto(Long id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Optional<TipoProduto> tipoProduto = tipoProdutoRepository.findById(id);
		return repository.findDistinctByTipoProduto(tipoProduto, pageRequest);	
	}
}
