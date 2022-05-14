package com.sapient.endur.service;

public class AirTicketInfoException extends Exception {

	private static final long serialVersionUID = 1L;

	public AirTicketInfoException() {
		
	}
	
	public AirTicketInfoException(String message) {
		super(message);
	}

	public AirTicketInfoException(String message, Throwable t) {
		super(message, t);
	}
	
}