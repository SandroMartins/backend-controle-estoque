package com.controle.estoque.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.controle.estoque.domain.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class MovimentacaoEstoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	private Integer tipoMovimentacao;
	private Double valorVenda;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT-3")
	private Date dataVenda;
	
	private Integer quantidade;

	public MovimentacaoEstoque() {
	}

	public MovimentacaoEstoque(Long id, Produto produto, TipoMovimentacao tipoMovimentacao, Double valorVenda, Date dataVenda,
			Integer quantidade) {
		super();
		this.id = id;
		this.produto = produto;
		this.tipoMovimentacao = tipoMovimentacao.getCod();
		this.valorVenda = valorVenda;
		this.dataVenda = dataVenda;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return TipoMovimentacao.toEnum(tipoMovimentacao);
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao.getCod();
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
