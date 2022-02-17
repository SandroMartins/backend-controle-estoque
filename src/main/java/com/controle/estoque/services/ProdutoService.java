package com.controle.estoque.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.controle.estoque.domain.Produto;
import com.controle.estoque.domain.TipoProduto;
import com.controle.estoque.domain.enums.TipoMovimentacao;
import com.controle.estoque.dto.ProdutoDTO;
import com.controle.estoque.repositories.ProdutoRepository;
import com.controle.estoque.repositories.TipoProdutoRepository;
import com.controle.estoque.services.exceptions.DataIntegrityException;
import com.controle.estoque.services.exceptions.ObjectNotFoundException;
import com.controle.estoque.services.exceptions.QuantidadeInsuficienteException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> findDistinctByTipoProduto(Long id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		if(id == null) {
			return repository.findAll(pageRequest);
		}
		
		Optional<TipoProduto> tipoProduto = tipoProdutoRepository.findById(id);
		return repository.findDistinctByTipoProduto(tipoProduto, pageRequest);	
	}
	
	public Produto insert(Produto produto) {
		return repository.save(produto);
	}
	
	public Produto update(Produto produto) {
		findById(produto.getId());
		return repository.save(produto);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
			
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um produto que possui movimentações de estoque.");
		}
	}
	
	public Produto fromDTO(ProdutoDTO prodDto) {
		TipoProduto tipoProduto = new TipoProduto(prodDto.getTipoProduto(), null);
		Produto produto = new Produto(null, prodDto.getDescricao(), tipoProduto, prodDto.getValorFornecedor(), prodDto.getQuantidade());
		
		return produto;
	}

	public void updateQuantidade(MovimentacaoEstoque movimentacaoEstoque) {
		Produto produto = findById(movimentacaoEstoque.getProduto().getId());
		Integer qtdAtualizada;
		
		if(movimentacaoEstoque.getTipoMovimentacao() == TipoMovimentacao.SAIDA
				&& produto.getQuantidade() < movimentacaoEstoque.getQuantidade()) {
			throw new QuantidadeInsuficienteException("Produto não possui quantidade suficiente no estoque.");
		}
		
		if(movimentacaoEstoque.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
			qtdAtualizada = produto.getQuantidade() - movimentacaoEstoque.getQuantidade();
			produto.setQuantidade(qtdAtualizada);
			
			repository.save(produto);
		}
		
		if(movimentacaoEstoque.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
			qtdAtualizada = produto.getQuantidade() + movimentacaoEstoque.getQuantidade();
			produto.setQuantidade(qtdAtualizada);
			
			repository.save(produto);
		}		
	}
}
