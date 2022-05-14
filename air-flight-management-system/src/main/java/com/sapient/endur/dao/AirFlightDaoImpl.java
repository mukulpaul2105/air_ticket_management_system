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
import com.sapient.endur.model.AirFlight;
import com.sapient.endur.util.MySQLConnection;

public class AirFlightDaoImpl implements AirFlightDao{
	private Logger logger= Logger.getLogger(AirFlightDaoImpl.class);

	@Override
	public AirFlight getAirFlightById(Integer flightId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_FLIGHTS_BY_ID);
			){
				
				preparedStatement.setInt(1, flightId);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					AirFlight airflight=new AirFlight();
					//read from ResultSet object and write into Employee object
					populateAirFlight(airflight,resultSet);
					logger.info("Returning flight details: "+ airflight);
					return airflight;
				}else {
					logger.warn("Invalid flight id");
					throw new SQLException("Invalid flight id");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}
	}

	private void populateAirFlight(AirFlight airflight, ResultSet resultSet) throws SQLException {
		airflight.setFlightId(resultSet.getInt("flight_id"));
		airflight.setAirlineId(resultSet.getString("airline_id"));
		airflight.setAirlineName(resultSet.getString("airline_name"));
		airflight.setFromLocation(resultSet.getString("from_location"));
		airflight.setToLocation(resultSet.getString("to_location"));
		airflight.setDepartureTime(resultSet.getTime("departure_time").toLocalTime());
		airflight.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
		airflight.setDuration(resultSet.getTime("duration").toLocalTime());
		airflight.setTotalSeats(resultSet.getInt("total_seats"));
		
		
		
	}

	@Override
	public List<AirFlight> getAllFlights() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_FLIGHTS);
				List<AirFlight> airflightList= new ArrayList<>();
				while(resultSet.next()) {
					AirFlight airflight = new AirFlight();
					populateAirFlight(airflight,resultSet);
					airflightList.add(airflight);				
				}
				if(airflightList.size()!=0) {
					logger.info("returning list of flights");
					return airflightList;
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
	public Integer addFlights(AirFlight airflight) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.ADD_FLIGHTS);				
			) {
				
				
				preparedStatement.setString(1, airflight.getAirlineId());
				preparedStatement.setString(2, airflight.getAirlineName());
				preparedStatement.setString(3, airflight.getFromLocation());
				preparedStatement.setString(4, airflight.getToLocation());
				preparedStatement.setTime(5, java.sql.Time.valueOf(airflight.getDepartureTime()));
				preparedStatement.setTime(6, java.sql.Time.valueOf(airflight.getArrivalTime()));
				preparedStatement.setTime(7, java.sql.Time.valueOf(airflight.getDuration()));
				preparedStatement.setInt(8, airflight.getTotalSeats());
				preparedStatement.setInt(9, airflight.getFlightId());
				
				//executeUpdate() can be applied for all DML statements
				//this methods no. of rows affected
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("flights added to database");
					return airflight.getFlightId();
					
				}else {				
					throw new SQLException("Unable add flights");
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
	public Integer deleteFlights(Integer flightId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.DELETE_FLIGHTS);
			){
				
				preparedStatement.setInt(1, flightId);
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return flightId;
				}else {
					throw new SQLException("Unable to delete flights");
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
	public Integer updateFlights(AirFlight airflight) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.UPDATE_FLIGHTS);
			){
			
			preparedStatement.setString(1, airflight.getAirlineId());
			preparedStatement.setString(2, airflight.getAirlineName());
			preparedStatement.setString(3, airflight.getFromLocation());
			preparedStatement.setString(4, airflight.getToLocation());
			preparedStatement.setTime(5, java.sql.Time.valueOf(airflight.getDepartureTime()));
			preparedStatement.setTime(6, java.sql.Time.valueOf(airflight.getArrivalTime()));
			preparedStatement.setTime(7, java.sql.Time.valueOf(airflight.getDuration()));
			preparedStatement.setInt(8, airflight.getTotalSeats());	
			preparedStatement.setInt(9, airflight.getFlightId());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return airflight.getFlightId();
				}else {
					throw new SQLException("Unable to update flights");
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
	public List<AirFlight> getFlights(String source, String destination) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_FLIGHTS);
			){
				
				preparedStatement.setString(1, source);
				preparedStatement.setString(2, destination);
				ResultSet resultSet= preparedStatement.executeQuery();
				List<AirFlight> airflightList= new ArrayList<>();
				while(resultSet.next()) {
					AirFlight airflight = new AirFlight();
					populateAirFlight(airflight,resultSet);
					airflightList.add(airflight);				
				}
				
				if(airflightList.size()!=0) {
					logger.info("returning list of all air flights");
					return airflightList;
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
}