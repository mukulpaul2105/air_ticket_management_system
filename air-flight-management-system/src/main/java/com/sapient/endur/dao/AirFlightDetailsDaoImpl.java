package com.sapient.endur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.mapper.QueryMapper;
import com.sapient.endur.model.AirFlightBooking;
import com.sapient.endur.model.AirFlightDetails;
import com.sapient.endur.util.MySQLConnection;

public class AirFlightDetailsDaoImpl implements AirFlightDetailsDAO {
private Logger logger= Logger.getLogger(AirFlightDetailsDaoImpl.class);
	
	@Override
	public Integer addFlights(AirFlightDetails airFlightDetails) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.ADD_AIR_FLIGHT_DETAILS);				
			) {
				//replace placeholders with actual values
				preparedStatement.setInt(1, airFlightDetails.getFlightId());
				preparedStatement.setDate(2, java.sql.Date.valueOf(airFlightDetails.getFlightDepatureDate()));
				preparedStatement.setDouble(3, airFlightDetails.getPricePerSeat());
				preparedStatement.setInt(4, airFlightDetails.getAvailableSeats());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("Air Flight Details added to database");
					return airFlightDetails.getFlightId();
					
				}else {				
					throw new SQLException("Unable add Air Flight Details");
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
	public Integer deleteFlights(Integer flightId, LocalDate flightDepatureDate) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.DELETE_AIR_FLIGHT_DETAILS);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setInt(1, flightId);
				preparedStatement.setDate(2, java.sql.Date.valueOf(flightDepatureDate));
				int n = preparedStatement.executeUpdate()
						;
				if(n>0) {
					return flightId;
				}else {
					throw new SQLException("Unable to delete Air Flight Details");
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
	public Integer updateFlights(AirFlightDetails airFlightDetails) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.UPDATE_AIR_FLIGHT_DETAILS);				
			) {
				//replace placeholders with actual values
			preparedStatement.setDouble(1, airFlightDetails.getPricePerSeat());
			preparedStatement.setInt(2, airFlightDetails.getAvailableSeats());
			preparedStatement.setInt(3,  airFlightDetails.getFlightId());
			preparedStatement.setDate(4, java.sql.Date.valueOf(airFlightDetails.getFlightDepatureDate()));
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return airFlightDetails.getFdId();
				}else {				
					throw new SQLException("Unable update Air Flight Details");
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
	public AirFlightDetails getAirFlightDetailsById(Integer flightId, LocalDate flightDepatureDate)
			throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_AIR_FLIGHT_DETAILS_BY_ID);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setInt(1, flightId);
				preparedStatement.setDate(2, java.sql.Date.valueOf(flightDepatureDate));
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					AirFlightDetails airFlightDetails = new AirFlightDetails();
					populateAirFlightDetails(airFlightDetails, resultSet);
					logger.info("Returning Passenger Profile details: "+ airFlightDetails);
					return airFlightDetails;
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
	

	private void populateAirFlightDetails(AirFlightDetails airFlightDetails, ResultSet resultSet) throws SQLException,Exception {
		airFlightDetails.setFdId(resultSet.getInt("fd_id"));
		airFlightDetails.setFlightId(resultSet.getInt("flight_id"));
		airFlightDetails.setFlightDepatureDate(resultSet.getDate("flight_departure_date").toLocalDate());
		airFlightDetails.setPricePerSeat(resultSet.getDouble("price_per_seat"));
		airFlightDetails.setAvailableSeats(resultSet.getInt("available_seats"));
		
	}

	@Override
	public List<AirFlightDetails> getAllFlightsDetails() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_AIR_FLIGHT_DETAILS);
				List<AirFlightDetails> airFlightDetailsList = new ArrayList<>();
				while(resultSet.next()) {
					AirFlightDetails airFlightDetails = new AirFlightDetails();
					//read from ResultSet object and write into Air Flight Details object
					populateAirFlightDetails(airFlightDetails,resultSet);
					airFlightDetailsList.add(airFlightDetails);				
				}
				if(airFlightDetailsList.size()!=0) {
					logger.info("returning list of all Air Flight Details");
					return airFlightDetailsList;
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
	public Integer updateAvailableTickets(AirFlightDetails airFlightDetails) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.UPDATE_AVAILABLE_SEATS);				
			) {
				//replace placeholders with actual values
			preparedStatement.setInt(1, airFlightDetails.getAvailableSeats());
			preparedStatement.setInt(2, airFlightDetails.getFdId());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return airFlightDetails.getFdId();
				}else {				
					throw new SQLException("Unable update Air Flight Details");
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
	public AirFlightDetails getAirFlightDetailsByFdId(Integer fdId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_FLIGHT_BY_FD_ID);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setInt(1, fdId);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					AirFlightDetails airFlightDetails = new AirFlightDetails();
					populateAirFlightDetails(airFlightDetails, resultSet);
					logger.info("Returning Air Flight Details details: "+ airFlightDetails);
					return airFlightDetails;
				}else {
					logger.warn("Invalid Fight ID");
					throw new SQLException("Invalid Flight ID");
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
