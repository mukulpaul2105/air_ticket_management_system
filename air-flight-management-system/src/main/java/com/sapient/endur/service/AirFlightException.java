package com.sapient.endur.service;

public class AirFlightException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AirFlightException() {
		super();
	}
	
	public AirFlightException(String message) {
		super(message);
	}
	
	public AirFlightException(String message,Throwable t) {
		super(message,t);
	}

}