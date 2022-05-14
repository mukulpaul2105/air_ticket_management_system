package com.sapient.endur.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.endur.model.AirPassengerProfile;

public interface AirPassengerProfileDAO {
	
	public abstract String addAirPassengerProfile(AirPassengerProfile passengerProfile) throws SQLException;
	public abstract String deleteAirPassengerProfile(String profileId) throws SQLException;
	public abstract String updateAirPassengerProfile(AirPassengerProfile passengerProfile) throws SQLException;
	public abstract AirPassengerProfile getAirPassengerProfileById(String profileId) throws SQLException;
	public abstract List<AirPassengerProfile> getAirPassengerProfiles() throws SQLException;
	
	public abstract String getLatestProfileId() throws SQLException;
	
}


 
