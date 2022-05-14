package com.sapient.endur.service;

public class AirFlightBookingException extends Exception {

	private static final long serialVersionUID = 1L;

	public AirFlightBookingException() {
		
	}
	
	public AirFlightBookingException(String message) {
		super(message);
	}

	public AirFlightBookingException(String message, Throwable t) {
		super(message, t);
	}
	
}
