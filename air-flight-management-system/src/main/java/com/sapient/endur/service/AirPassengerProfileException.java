package com.sapient.endur.service;

public class AirPassengerProfileException extends Exception{

	private static final long serialVersionUID = 1L;

	public AirPassengerProfileException() {
		super();
	}
	
	public AirPassengerProfileException(String message) {
		super(message);
	}
	
	public AirPassengerProfileException(String message,Throwable t) {
		super(message, t);
	}
	
}
