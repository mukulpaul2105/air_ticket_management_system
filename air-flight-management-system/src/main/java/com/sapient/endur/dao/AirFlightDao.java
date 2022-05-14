package com.sapient.endur.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.endur.model.AirFlight;




public interface AirFlightDao {
	public abstract Integer addFlights(AirFlight airflight) throws SQLException;
	public abstract Integer deleteFlights(Integer flightId) throws SQLException;
	public abstract Integer updateFlights(AirFlight airflight) throws SQLException;
	public abstract AirFlight getAirFlightById(Integer flightId) throws SQLException;
	public abstract List<AirFlight> getAllFlights() throws SQLException;

	public abstract List<AirFlight> getFlights(String source, String destination) throws SQLException;
	
	

}
