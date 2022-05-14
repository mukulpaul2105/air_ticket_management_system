package com.sapient.endur.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.endur.model.AirFlightBooking;

public interface AirFlightBookingDAO {
	
	public abstract Integer addAirFlightBooking(AirFlightBooking flightBooking) throws SQLException;
	public abstract Integer deleteAirFlightBooking(Integer bookingId) throws SQLException;
	public abstract Integer updateAirFlightBooking(AirFlightBooking flightBooking) throws SQLException;
	public abstract AirFlightBooking getAirFlightBookingById(Integer bookingId) throws SQLException;
	public abstract List<AirFlightBooking> getAirFlightBookings() throws SQLException;
	public abstract Integer getLatestFlightBookingId() throws SQLException;
}


