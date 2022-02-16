package com.controle.estoque.domain.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.controle.estoque.domain.Produto;
import com.controle.estoque.domain.enums.TipoMovimentacao;
import com.controle.estoque.domain.repositories.MovimentacaoEstoqueRepository;
import com.controle.estoque.domain.services.exceptions.ObjectNotFoundException;
import com.controle.estoque.dto.MovimentacaoEstoqueDTO;

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
