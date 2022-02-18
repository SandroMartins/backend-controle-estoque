package com.controle.estoque.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.controle.estoque.domain.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "tipo_produto_id")
	private TipoProduto tipoProduto;
	
	private Double valorFornecedor;
	
	@Min(value = 0, message= "Valor minimo deve ser 0")
	private Integer quantidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
	
	public Produto() {
	}

	public Produto(Long id, String descricao, TipoProduto tipoProduto, Double valorFornecedor, Integer quantidade) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipoProduto = tipoProduto;
		this.valorFornecedor = valorFornecedor;
		this.quantidade = quantidade;
	}
	
	public double getTotalLucro() {
		double lucro = 0.0;
		double lucroTotal = 0.0;
		for (MovimentacaoEstoque me : getMovimentacoes()) {
			if(me.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
				lucro = (me.getValorVenda() - getValorFornecedor()) * me.getQuantidade();
				lucroTotal = lucroTotal + lucro;
			}
		}
		return lucroTotal;
	}
	
	public int getQuantidadeTotalSaidaProduto() {
		int qtdTotalSaida = 0;
		for (MovimentacaoEstoque me : getMovimentacoes()) {
			if(me.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
				qtdTotalSaida = qtdTotalSaida + me.getQuantidade();
			}
		}
		return qtdTotalSaida;
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

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
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
	
	public List<MovimentacaoEstoque> getMovimentacoes() {
		return movimentacoes;
	}
	
	public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
}
