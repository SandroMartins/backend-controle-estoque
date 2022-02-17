package com.controle.estoque.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.controle.estoque.domain.Produto;
import com.controle.estoque.domain.enums.TipoMovimentacao;
import com.controle.estoque.dto.MovimentacaoEstoqueDTO;
import com.controle.estoque.repositories.MovimentacaoEstoqueRepository;
import com.controle.estoque.services.exceptions.ObjectNotFoundException;

@Service
public class MovimentacaoEstoqueService {
	
	@Autowired
	private MovimentacaoEstoqueRepository repository;
	
	@Autowired
	private ProdutoService produtoService;

	public MovimentacaoEstoque findById(Long id) {
		Optional<MovimentacaoEstoque> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + MovimentacaoEstoque.class.getName()));
	}
	
	public Page<MovimentacaoEstoque> findDistinctByProduto(Long id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		System.out.println("AQUI: "+id);
		Produto produto = produtoService.findById(id);
		System.out.println("AQUI2: "+produto);
		return repository.findDistinctByProduto(produto, pageRequest);	
	}
	
	public MovimentacaoEstoque fromDTO(MovimentacaoEstoqueDTO movDto) {
		Produto produto = produtoService.findById(movDto.getProdutoId());
		MovimentacaoEstoque movimentacaoEstoque = 
				new MovimentacaoEstoque(null, produto,
						TipoMovimentacao.toEnum(movDto.getTipoMovimentacao()), movDto.getValorVenda(), 
						movDto.getDataVenda(), movDto.getQuantidade());

		return movimentacaoEstoque;
	}
	
	@Transactional
	public MovimentacaoEstoque insert(MovimentacaoEstoque movimentacaoEstoque) {
		produtoService.updateQuantidade(movimentacaoEstoque);
		return repository.save(movimentacaoEstoque);
	}
}
