package com.sapient.endur.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.sapient.endur.model.AirFlightDetails;

public interface AirFlightDetailsDAO {

	public abstract Integer addFlights(AirFlightDetails airFlightDetails) throws SQLException;
	public abstract Integer deleteFlights(Integer flightId, LocalDate flightDepatureDate) throws SQLException;
	public abstract Integer updateFlights(AirFlightDetails airFlightDetails) throws SQLException;
	public abstract AirFlightDetails getAirFlightDetailsById(Integer flightId, LocalDate flightDepatureDate) throws SQLException;
	public abstract List<AirFlightDetails> getAllFlightsDetails() throws SQLException;

	public abstract AirFlightDetails getAirFlightDetailsByFdId(Integer fdId) throws SQLException;
	public abstract Integer updateAvailableTickets(AirFlightDetails airFlightDetails) throws SQLException;
	
}
