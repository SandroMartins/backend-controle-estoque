package com.controle.estoque.domain.enums;

public enum TipoMovimentacao {
	
	ENTRADA(1, "Entrada"),
	SAIDA(2, "Saída");
	
	private int cod;
	private String descricao;
	
	private TipoMovimentacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoMovimentacao toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}

		for(TipoMovimentacao tipo : TipoMovimentacao.values()) {
			if(cod.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod); 
	}
}
