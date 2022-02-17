package com.controle.estoque.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MovimentacaoEstoqueDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long produtoId;

	private Integer tipoMovimentacao;
	private Double valorVenda;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone="GMT-3")
	private Date dataVenda;
	
	@Min(value = 1, message = "Valor minimo deve ser 1")
	private Integer quantidade;
	
	public MovimentacaoEstoqueDTO() {
		
	}

	public MovimentacaoEstoqueDTO(Long id, Long produtoId, Integer tipoMovimentacao, Double valorVenda, Date dataVenda,
			Integer quantidade) {
		super();
		this.id = id;
		this.produtoId = produtoId;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valorVenda = valorVenda;
		this.dataVenda = dataVenda;
		this.quantidade = quantidade;
	}
	
	public MovimentacaoEstoqueDTO(MovimentacaoEstoque obj) {
		id = obj.getId();
		produtoId = obj.getProduto().getId();
		tipoMovimentacao = obj.getTipoMovimentacao().getCod();
		valorVenda = obj.getValorVenda();
		dataVenda = obj.getDataVenda();
		quantidade = obj.getQuantidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(Integer tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
