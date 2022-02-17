package com.controle.estoque.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.dto.TipoProdutoDTO;
import com.controle.estoque.repositories.TipoProdutoRepository;
import com.controle.estoque.services.exceptions.DataIntegrityException;
import com.controle.estoque.services.exceptions.ObjectNotFoundException;

@Service
public class TipoProdutoService {
	
	@Autowired
	private TipoProdutoRepository repository;

	public TipoProduto findById(Long id) {
		Optional<TipoProduto> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + TipoProduto.class.getName()));
	}
	
	public TipoProduto insert(TipoProduto tipoProduto) {
		return repository.save(tipoProduto);
	}
	
	public TipoProduto update(TipoProduto tipoProduto) {
		findById(tipoProduto.getId());
		return repository.save(tipoProduto);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
			
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Tipo Produto que possui Produtos vinculados.");
		}
	}
	
	public TipoProduto fromDTO(TipoProdutoDTO tipoProdDto) {
		return new TipoProduto(tipoProdDto.getId(), tipoProdDto.getDescricao());
	}
}
