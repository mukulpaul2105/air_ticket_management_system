package com.sapient.endur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.mapper.QueryMapper;
import com.sapient.endur.model.AirPassengerProfile;
import com.sapient.endur.util.MySQLConnection;

public class AirPassengerProfileDaoImpl implements AirPassengerProfileDAO {
	private Logger logger= Logger.getLogger(AirPassengerProfileDaoImpl.class);

	@Override
	public String addAirPassengerProfile(AirPassengerProfile passengerProfile) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.ADD_PASSENGER);				
			) {
				//replace placeholders with actual values
				preparedStatement.setString(1, passengerProfile.getProfileId());
				preparedStatement.setString(2, passengerProfile.getPassword());
				preparedStatement.setString(3, passengerProfile.getFirstName());
				preparedStatement.setString(4, passengerProfile.getLastName());
				preparedStatement.setString(5, passengerProfile.getAddress());
				preparedStatement.setLong(6, passengerProfile.getMobile());
				preparedStatement.setString(7, passengerProfile.getEmailId());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("Passenger Profile added to database");
					return passengerProfile.getProfileId();
				}else {				
					throw new SQLException("Unable add Passenger Profile");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}			
	}

	@Override
	public String deleteAirPassengerProfile(String profileId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.DELETE_PASSENGER);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setString(1, profileId);
				int n = preparedStatement.executeUpdate()
						;
				if(n>0) {
					return profileId;
				}else {
					throw new SQLException("Unable to delete Passenger Profile");
				}
				
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}	
	}

	@Override
	public String updateAirPassengerProfile(AirPassengerProfile passengerProfile) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.UPDATE_PASSENGER);				
			) {
				//replace placeholders with actual values
				preparedStatement.setString(1, passengerProfile.getPassword());
				preparedStatement.setString(2, passengerProfile.getFirstName());
				preparedStatement.setString(3, passengerProfile.getLastName());
				preparedStatement.setString(4, passengerProfile.getAddress());
				preparedStatement.setLong(5, passengerProfile.getMobile());
				preparedStatement.setString(6, passengerProfile.getEmailId());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return passengerProfile.getProfileId();
				}else {				
					throw new SQLException("Unable update Passenger Profile");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}			
	}

	@Override
	public AirPassengerProfile getAirPassengerProfileById(String profileId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_PASSENGER_BY_ID);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setString(1, profileId);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					AirPassengerProfile passengerProfile = new AirPassengerProfile();
					//read from ResultSet object and write into Employee object
					populateAirPassengerProfile(passengerProfile, resultSet);
					logger.info("Returning Passenger Profile details: "+ passengerProfile);
					return passengerProfile;
				}else {
					logger.warn("Invalid Profile ID");
					throw new SQLException("Invalid Profile ID");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}		
	}

	private void populateAirPassengerProfile(AirPassengerProfile passengerProfile, ResultSet resultSet) throws SQLException,Exception {
		passengerProfile.setProfileId(resultSet.getString("profile_id"));
		passengerProfile.setPassword(resultSet.getString("password"));
		passengerProfile.setFirstName((resultSet.getString("first_name")));
		passengerProfile.setLastName(resultSet.getString("last_name"));
		passengerProfile.setAddress(resultSet.getString("address"));
		passengerProfile.setMobile(resultSet.getLong("mobile_number"));
		passengerProfile.setEmailId(resultSet.getString("email_id"));
		
	}

	@Override
	public List<AirPassengerProfile> getAirPassengerProfiles() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_PASSENGERS);
				List<AirPassengerProfile> passengerProfileList = new ArrayList<>();
				while(resultSet.next()) {
					AirPassengerProfile passengerProfile = new AirPassengerProfile();
					//read from ResultSet object and write into Employee object
					populateAirPassengerProfile(passengerProfile,resultSet);
					passengerProfileList.add(passengerProfile);				
				}
				if(passengerProfileList.size()!=0) {
					logger.info("returning list of all Passenger Profile");
					return passengerProfileList;
				}else {
					logger.info("The table is empty");
					return null;
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}	
	}

	@Override
	public String getLatestProfileId() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_Latest_PROFILE_ID);
				if(resultSet.next()) {
					String id= resultSet.getString(1);
					return id;
				}else {
					return null;
				}
		}catch(SQLException e) {
			logger.error(e);
			throw e;
		}catch(Exception e) {
			logger.error(e);
			throw new SQLException(e);
		}	
	}


	
}
