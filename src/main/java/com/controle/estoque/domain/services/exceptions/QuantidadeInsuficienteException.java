package com.controle.estoque.domain.services.exceptions;

public class QuantidadeInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuantidadeInsuficienteException(String msg) {
		super(msg);
	}

	public QuantidadeInsuficienteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
