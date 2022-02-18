package com.controle.estoque.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.controle.estoque.domain.Produto;


public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 20, message = "O tamanho deve ser entre 3 e 20 caracteres")
	private String descricao;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Long tipoProduto;
	
	private Double valorFornecedor;
	private Integer quantidade;
	
	
	public ProdutoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(Long tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Double getValorFornecedor() {
		return valorFornecedor;
	}

	public void setValorFornecedor(Double valorFornecedor) {
		this.valorFornecedor = valorFornecedor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
