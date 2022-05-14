package com.sapient.endur.service;

import java.time.LocalDate;
import java.util.List;

import com.sapient.endur.model.AirFlightDetails;

public interface AirFlightDetailsService {
	public abstract Integer addAirFlightDetails(AirFlightDetails flightDetails) throws AirFlightDetailsException;
	public abstract Integer deleteAirFlightDetails(Integer flightId, LocalDate flightDepartureDate) throws AirFlightDetailsException;
	public abstract Integer updateAirFlightDetails(AirFlightDetails flightDetails) throws AirFlightDetailsException;
	public abstract AirFlightDetails getAirFlightDetailsById(Integer flightId, LocalDate flightDepartureDate) throws AirFlightDetailsException;
	public abstract List<AirFlightDetails> getAirFlightDetails() throws AirFlightDetailsException;

	public abstract AirFlightDetails getAirFlightDetailsByFdId(Integer fdId) throws AirFlightDetailsException;
	public abstract Integer updateAvailableTickets(AirFlightDetails airFlightDetails) throws AirFlightDetailsException;
}
