package com.sapient.endur.service;

public class AirFlightDetailsException extends Exception {
	private static final long serialVersionUID = 1L;

	public AirFlightDetailsException() {
		
	}
	
	public AirFlightDetailsException(String message) {
		super(message);
	}

	public AirFlightDetailsException(String message, Throwable t) {
		super(message, t);
	}
}
