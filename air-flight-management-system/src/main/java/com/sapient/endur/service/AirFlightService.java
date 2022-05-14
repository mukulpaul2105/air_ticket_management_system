package com.sapient.endur.service;

import java.util.List;

import com.sapient.endur.model.AirFlight;




public interface AirFlightService {
	public abstract Integer addFlights(AirFlight airflight) throws AirFlightException;
	public abstract Integer deleteFlights(Integer flightId) throws AirFlightException;
	public abstract Integer updateFlights(AirFlight airflight) throws AirFlightException;
	public abstract AirFlight getAirFlightById(Integer flightId) throws AirFlightException;
	public abstract List<AirFlight> getAllFlights() throws  AirFlightException;
	public abstract List<AirFlight> getFlights(String source, String destination) throws  AirFlightException;
	
}