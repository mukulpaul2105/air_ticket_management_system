package com.sapient.endur.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.dao.AirFlightBookingDAO;
import com.sapient.endur.dao.AirFlightBookingDaoImpl;
import com.sapient.endur.model.AirFlightBooking;

public class AirFlightBookingServiceImpl implements AirFlightBookingService{
	
	private AirFlightBookingDAO flightBookingDao = new AirFlightBookingDaoImpl();
	private Logger logger = Logger.getLogger(AirFlightBookingServiceImpl.class);
	private Validator validator = new Validator();

	@Override
	public Integer addAirFlightBooking(AirFlightBooking flightBooking) throws AirFlightBookingException {
		try {
			if(validateFlightBooking(flightBooking)) {
				logger.info("Flight Booking object is valid");
				return flightBookingDao.addAirFlightBooking(flightBooking);
				
			} else {
				logger.warn("Invalid Flight Booking Details");
				return null;
			}
			
		}  catch(SQLException e) {
			throw new AirFlightBookingException(e.getMessage(),e);
			
		} catch(Exception e) {
			throw new AirFlightBookingException(e.getMessage(),e);
		}
	}

	private boolean validateFlightBooking(AirFlightBooking flightBooking) throws SQLException {
			if(validator.isValidFlightId(flightBooking.getFlightId())) {
				if(validator.isValidFlightDepatureDate(flightBooking.getFlightDepatureDate())) {
					if(validator.isValidBookedBy(flightBooking.getBookedBy())) {
						if(validator.isValidTicketBooked(flightBooking.getTicketsBooked())) {
							logger.info("In service layer, Flight Booking Object is valid");
							return true;
						}
					}
				}
			}
		return false;
	}

	@Override
	public Integer deleteAirFlightBooking(Integer bookingId) throws AirFlightBookingException {

		try {
			return flightBookingDao.deleteAirFlightBooking(bookingId);
			
		} catch(SQLException e) {
			throw new AirFlightBookingException(e.getMessage(),e);
			
		} catch(Exception e) {
			throw new AirFlightBookingException(e.getMessage(),e);
		}
		
	}

	@Override
	public Integer updateAirFlightBooking(AirFlightBooking flightBooking) throws AirFlightBookingException {

		try {
			if(validateFlightBooking(flightBooking)) {
				return flightBookingDao.updateAirFlightBooking(flightBooking);
				
			}
			logger.warn("Invalid Flight Booking Details");
			return null;
		} catch(SQLException e) {
			throw new AirFlightBookingException(e.getMessage(),e);
			
		} catch(Exception e) {
			throw new AirFlightBookingException(e.getMessage(),e);
		}
		
	}

	@Override
	public AirFlightBooking getAirFlightBookingById(Integer bookingId) throws AirFlightBookingException {

		try {
			return flightBookingDao.getAirFlightBookingById(bookingId);
			
		} catch(SQLException e) {
			throw new AirFlightBookingException(e.getMessage(),e);
			
		} catch(Exception e) {
			throw new AirFlightBookingException(e.getMessage(),e);
		}
		
	}

	@Override
	public List<AirFlightBooking> getAirFlightBookings() throws AirFlightBookingException {
		try {
			return flightBookingDao.getAirFlightBookings();
			
		} catch(SQLException e) {
			throw new AirFlightBookingException(e.getMessage(),e);
			
		} catch(Exception e) {
			throw new AirFlightBookingException(e.getMessage(),e);
		}
		
	}

	@Override
	public Integer getLatestFlightBookingId() throws AirFlightBookingException {

		try {
			return flightBookingDao.getLatestFlightBookingId();
			
		} catch(SQLException e) {
			throw new AirFlightBookingException(e.getMessage(),e);
			
		} catch(Exception e) {
			throw new AirFlightBookingException(e.getMessage(),e);
		}
		
	}

}
