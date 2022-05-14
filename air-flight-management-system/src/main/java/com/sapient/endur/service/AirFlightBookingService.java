package com.sapient.endur.service;

import java.util.List;

import com.sapient.endur.model.AirFlightBooking;

public interface AirFlightBookingService {
	public abstract Integer addAirFlightBooking(AirFlightBooking flightBooking) throws AirFlightBookingException;
	public abstract Integer deleteAirFlightBooking(Integer bookingId) throws AirFlightBookingException;
	public abstract Integer updateAirFlightBooking(AirFlightBooking flightBooking) throws AirFlightBookingException;
	public abstract AirFlightBooking getAirFlightBookingById(Integer bookingId) throws AirFlightBookingException;
	public abstract List<AirFlightBooking> getAirFlightBookings() throws AirFlightBookingException;
	public abstract Integer getLatestFlightBookingId() throws AirFlightBookingException;
}
