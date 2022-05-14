package com.sapient.endur.service;

import java.sql.SQLException;
import java.util.List;

import com.sapient.endur.model.AirPassengerProfile;

public interface AirPassengerProfileService {
	public abstract String addAirPassengerProfile(AirPassengerProfile passengerProfile) throws  AirPassengerProfileException;
	public abstract String deleteAirPassengerProfile(String profileId) throws AirPassengerProfileException;
	public abstract String updateAirPassengerProfile(AirPassengerProfile passengerProfile) throws AirPassengerProfileException;
	public abstract AirPassengerProfile getAirPassengerProfileById(String profileId) throws AirPassengerProfileException;
	public abstract List<AirPassengerProfile> getAirPassengerProfiles() throws AirPassengerProfileException;
	public abstract String getLatestProfileId() throws AirPassengerProfileException;
	
}
