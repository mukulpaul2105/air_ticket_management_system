package com.sapient.endur.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.dao.AirFlightDetailsDAO;
import com.sapient.endur.dao.AirFlightDetailsDaoImpl;
import com.sapient.endur.model.AirFlightDetails;

public class AirFlightDetailsServiceImpl implements AirFlightDetailsService {

	private AirFlightDetailsDAO flightDetailsDao = new AirFlightDetailsDaoImpl();
	private Logger logger = Logger.getLogger(AirFlightDetailsServiceImpl.class);
	private Validator validator = new Validator();

	@Override
	public Integer addAirFlightDetails(AirFlightDetails flightDetails) throws AirFlightDetailsException {
		try {
			if(validateFlightDetails(flightDetails)) {
				logger.info("Flight Details object is valid");
				return flightDetailsDao.addFlights(flightDetails);

			} else {
				logger.warn("Invalid Flight Details");
				return null;
			}

		}  catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}
	}

	private boolean validateFlightDetails(AirFlightDetails flightDetails) {
		if(validator.isValidFlightId(flightDetails.getFlightId())) {
			if(validator.isValidFlightDepatureDate(flightDetails.getFlightDepatureDate())) {
				if(validator.isValidPrice(flightDetails.getPricePerSeat())) {
					if(validator.isValidAirFlightId(flightDetails.getAvailableSeats())) {
						logger.info("In service layer, Flight Details Object is valid");
						return true;
					}
				}
			}
		}
		return false;
	}


	@Override
	public Integer updateAirFlightDetails(AirFlightDetails flightDetails) throws AirFlightDetailsException {
		try {
			if(validateFlightDetails(flightDetails)) {
				return flightDetailsDao.updateFlights(flightDetails);

			}
			logger.warn("Invalid Flight Details");
			return null;
		} catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteAirFlightDetails(Integer flightId, LocalDate flightDepartureDate)
			throws AirFlightDetailsException {
		try {
			return flightDetailsDao.deleteFlights(flightId, flightDepartureDate);

		} catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}	
	}


	@Override
	public AirFlightDetails getAirFlightDetailsById(Integer flightId, LocalDate flightDepartureDate)
			throws AirFlightDetailsException {
		try {
			return flightDetailsDao.getAirFlightDetailsById(flightId, flightDepartureDate);

		} catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}	
	}


	@Override
	public List<AirFlightDetails> getAirFlightDetails() throws AirFlightDetailsException {
		try {
			return flightDetailsDao.getAllFlightsDetails();

		} catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}
	}

	@Override
	public AirFlightDetails getAirFlightDetailsByFdId(Integer fdId) throws AirFlightDetailsException {
		try {
			return flightDetailsDao.getAirFlightDetailsByFdId(fdId);

		} catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}	
	}

	@Override
	public Integer updateAvailableTickets(AirFlightDetails airFlightDetails) throws AirFlightDetailsException {
		try {
			if(validateFlightDetailsT(airFlightDetails)) {
				return flightDetailsDao.updateAvailableTickets(airFlightDetails);

			}
			logger.warn("Invalid Flight Details");
			return null;
		} catch(SQLException e) {
			throw new AirFlightDetailsException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirFlightDetailsException(e.getMessage(),e);
		}
	}

	private boolean validateFlightDetailsT(AirFlightDetails airFlightDetails) {

		if(validator.isValidAirFlightIdT(airFlightDetails.getAvailableSeats())) {
			logger.info("In service layer, Flight Details Object is valid");
			return true;
		}
		return false;
	}




}
