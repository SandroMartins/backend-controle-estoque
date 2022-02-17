package com.controle.estoque.services.exceptions;

public class EmailRepetidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailRepetidoException(String msg) {
		super(msg);
	}

	public EmailRepetidoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
