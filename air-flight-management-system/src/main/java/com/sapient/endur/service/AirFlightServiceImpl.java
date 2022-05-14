package com.sapient.endur.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.dao.AirFlightDao;
import com.sapient.endur.dao.AirFlightDaoImpl;
import com.sapient.endur.model.AirFlight;

public class AirFlightServiceImpl implements AirFlightService  {
	private AirFlightDao airFlightDao= new AirFlightDaoImpl();
	private Logger logger= Logger.getLogger(AirFlightServiceImpl.class);
	private Validator airflightvalidator= new Validator();
	private AirFlightDaoImpl airFlightDaoImpl = new AirFlightDaoImpl();



	@Override
	public Integer addFlights(AirFlight airflight) throws AirFlightException {
		try {

			if(validateAirFlight(airflight)) {
				logger.info("airflight object is valid");
				return airFlightDaoImpl.addFlights(airflight);
			}else { 
				logger.warn("Invalid flight Details");
				return null;
			}
		}catch(SQLException e) {
			throw new AirFlightException(e.getMessage());
		}catch(Exception e) {
			throw new AirFlightException(e.getMessage());
		}
	}

	private boolean validateAirFlight(AirFlight airflight) {
		if(airflightvalidator.isValidAirFlightId(airflight.getFlightId())) {
			if(airflightvalidator.isValidAirlineId(airflight.getAirlineId())) {
				if(airflightvalidator.isValidAirlineName(airflight.getAirlineName())) {
					if(airflightvalidator.isValidFromLocation(airflight.getFromLocation())) {
						if(airflightvalidator.isValidToLocation(airflight.getToLocation())) {
							if(airflightvalidator.isValidDepartureTime(airflight.getDepartureTime())) {
								if(airflightvalidator.isValidArrivalTime(airflight.getArrivalTime())) {
									if(airflightvalidator.isValidDuration(airflight.getDuration())) {
										if(airflightvalidator.isValidTotalSeats(airflight.getTotalSeats())) {
											logger.info("In service layer, employee object is valid");
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}


	@Override
	public Integer deleteFlights(Integer flightId) throws AirFlightException  {
		try {
			return airFlightDao.deleteFlights(flightId);	

		}catch(SQLException e) {
			throw new AirFlightException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirFlightException(e.getMessage(),e);
		}
	}
	@Override
	public Integer updateFlights(AirFlight airflight) throws AirFlightException  {
		try {
			return airFlightDao.updateFlights(airflight);	

		} catch(SQLException e) {
			throw new AirFlightException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirFlightException(e.getMessage(),e);
		}
	}

	@Override
	public AirFlight getAirFlightById(Integer flightId) throws AirFlightException {
		try {
			return airFlightDao.getAirFlightById(flightId);	

		}catch(SQLException e) {
			throw new AirFlightException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirFlightException(e.getMessage(),e);
		}
	}

	@Override
	public List<AirFlight> getAllFlights() throws AirFlightException  {
		try {
			return airFlightDao.getAllFlights();	

		}catch(SQLException e) {
			throw new AirFlightException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirFlightException(e.getMessage(),e);
		}
	}


	@Override
	public List<AirFlight> getFlights(String source, String destination) throws AirFlightException {
		try {
			return airFlightDao.getFlights(source, destination);

		} catch(SQLException e) {
			throw new AirFlightException(e.getMessage(),e);
		} catch(Exception e) {
			throw new AirFlightException(e.getMessage(),e);
		}
	}
}