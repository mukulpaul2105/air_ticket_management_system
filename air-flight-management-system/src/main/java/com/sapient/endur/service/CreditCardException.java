package com.sapient.endur.service;

public class CreditCardException extends Exception {
	private static final long serialVersionUID = 1L;
	public CreditCardException() {
		super();
	}
	
	public CreditCardException(String message) {
		super(message);
	}
	
	public CreditCardException(String message,Throwable t) {
		super(message,t);
	}

}
