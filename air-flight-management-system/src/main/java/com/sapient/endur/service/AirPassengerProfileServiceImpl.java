package com.sapient.endur.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.dao.AirPassengerProfileDAO;
import com.sapient.endur.dao.AirPassengerProfileDaoImpl;
import com.sapient.endur.model.AirPassengerProfile;

public class AirPassengerProfileServiceImpl implements AirPassengerProfileService{

	private AirPassengerProfileDAO passengerProfileDao = new AirPassengerProfileDaoImpl();
	private Logger logger = Logger.getLogger(AirPassengerProfileServiceImpl.class);
	private Validator validator = new Validator();

	@Override
	public String addAirPassengerProfile(AirPassengerProfile passengerProfile) throws AirPassengerProfileException {

		try {
			if(validateAirPassengerProfile(passengerProfile)) {
				logger.info("Air Passenger Profile is valid");
				return passengerProfileDao.addAirPassengerProfile(passengerProfile);

			} else {
				logger.warn("Invalid Air Passenger Profile Details");
				return null;
			}

		} catch(SQLException e) {
			throw new AirPassengerProfileException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirPassengerProfileException(e.getMessage(),e);
		}

	}

	private boolean validateAirPassengerProfile(AirPassengerProfile passengerProfile) throws SQLException {

		if(validator.isValidProfileId(passengerProfile.getProfileId())) {
			if(validator.isValidPassword(passengerProfile.getPassword())) {
				if(validator.isValidFirstName(passengerProfile.getFirstName())) {
					if(validator.isValidLastName(passengerProfile.getLastName())) {
						if(validator.isValidAddress(passengerProfile.getAddress())) {
							if(validator.isValidMobile(passengerProfile.getMobile())) {
								if(validator.isValidEmail(passengerProfile.getEmailId())) {
									logger.info("In service layer, Passenger Profile is valid");
									return true;
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
	public String deleteAirPassengerProfile(String profileId) throws AirPassengerProfileException {
		try {
			return passengerProfileDao.deleteAirPassengerProfile(profileId);

		} catch(SQLException e) {
			throw new AirPassengerProfileException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirPassengerProfileException(e.getMessage(),e);
		}
	}

	@Override
	public String updateAirPassengerProfile(AirPassengerProfile passengerProfile) throws AirPassengerProfileException {
		try {
			if(validateAirPassengerProfile(passengerProfile)) {
				return passengerProfileDao.updateAirPassengerProfile(passengerProfile);
			}
			logger.warn("Invalid Passenger Profile Details");
			return null;
		} catch(SQLException e) {
			throw new AirPassengerProfileException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirPassengerProfileException(e.getMessage(),e);
		}

	}

	@Override
	public AirPassengerProfile getAirPassengerProfileById(String profileId) throws AirPassengerProfileException {
		try {
			return passengerProfileDao.getAirPassengerProfileById(profileId);

		}catch(SQLException e) {
			throw new AirPassengerProfileException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirPassengerProfileException(e.getMessage(),e);
		}
	}

	@Override
	public List<AirPassengerProfile> getAirPassengerProfiles() throws AirPassengerProfileException {
		try {
			return passengerProfileDao.getAirPassengerProfiles();

		} catch(SQLException e) {
			throw new AirPassengerProfileException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirPassengerProfileException(e.getMessage(),e);
		}
	}

	@Override
	public String getLatestProfileId() throws AirPassengerProfileException {
		try {
			return passengerProfileDao.getLatestProfileId();

		} catch(SQLException e) {
			throw new AirPassengerProfileException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirPassengerProfileException(e.getMessage(),e);
		}
	}


}
