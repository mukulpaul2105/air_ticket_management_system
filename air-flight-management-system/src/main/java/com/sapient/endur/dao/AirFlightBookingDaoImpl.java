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
import com.sapient.endur.model.AirFlightBooking;
import com.sapient.endur.util.MySQLConnection;

public class AirFlightBookingDaoImpl implements AirFlightBookingDAO {

	private Logger logger= Logger.getLogger(AirFlightBookingDaoImpl.class);

	@Override
	public Integer addAirFlightBooking(AirFlightBooking flightBooking) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.ADD_FLIGHT_BOOKING);				
			) {
				//replace placeholders with actual values
				preparedStatement.setInt(1, flightBooking.getFlightId());
				preparedStatement.setDate(2, java.sql.Date.valueOf(flightBooking.getFlightDepatureDate()));
				preparedStatement.setString(3, flightBooking.getBookedBy());
				preparedStatement.setInt(4, flightBooking.getTicketsBooked());
				
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("Flight Booking Details added to database");
					return flightBooking.getBookingId();
				}else {				
					throw new SQLException("Unable add Flight Booking Detials");
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
	public Integer deleteAirFlightBooking(Integer bookingId) throws SQLException {

		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.DELETE_FLIGHT_BOOKING);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setInt(1, bookingId);
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return bookingId;
				}else {
					throw new SQLException("Unable to delete Flight Booking");
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
	public Integer updateAirFlightBooking(AirFlightBooking flightBooking) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.UPDATE_FLIGHT_BOOKING);				
			) {
				//replace placeholders with actual values
				preparedStatement.setInt(1, flightBooking.getFlightId());
				preparedStatement.setDate(2, java.sql.Date.valueOf(flightBooking.getFlightDepatureDate()));
				preparedStatement.setString(3, flightBooking.getBookedBy());
				preparedStatement.setInt(4, flightBooking.getTicketsBooked());
				preparedStatement.setInt(5, flightBooking.getBookingId());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("Flight Booking Details Updated to database");
					return flightBooking.getBookingId();
				}else {				
					throw new SQLException("Unable Update Flight Booking Detials");
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
	public AirFlightBooking getAirFlightBookingById(Integer bookingId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_FLIGHT_BOOKING_BY_ID);
			){
				//replaced placeholder(?) with empno this method receives
				preparedStatement.setInt(1, bookingId);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					AirFlightBooking flightBooking = new AirFlightBooking();
					//read from ResultSet object and write into Employee object
					populateAirFlightBooking(flightBooking, resultSet);
					logger.info("Returning Fligth Booking details: "+ flightBooking);
					return flightBooking;
				}else {
					logger.warn("Invalid Booking ID");
					throw new SQLException("Invalid Booking ID");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}		
	}



	private void populateAirFlightBooking(AirFlightBooking flightBooking, ResultSet resultSet) throws SQLException,Exception {
		flightBooking.setBookingId(resultSet.getInt("booking_id"));
		flightBooking.setFlightId(resultSet.getInt("flight_id"));
		flightBooking.setFlightDepatureDate(resultSet.getDate("flight_departure_date").toLocalDate());
		flightBooking.setBookedBy(resultSet.getString("booked_by"));
		flightBooking.setTicketsBooked(resultSet.getInt("tickets_booked"));
		
		
	}

	@Override
	public List<AirFlightBooking> getAirFlightBookings() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_FLIGHT_BOOKING);
				List<AirFlightBooking> flightBookingList = new ArrayList<>();
				while(resultSet.next()) {
					AirFlightBooking flightBooking = new AirFlightBooking();
					populateAirFlightBooking(flightBooking,resultSet);
					flightBookingList.add(flightBooking);				
				}
				if(flightBookingList.size()!=0) {
					logger.info("returning list of all Flight Bookiing Details");
					return flightBookingList;
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
	public Integer getLatestFlightBookingId() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_LATEST_ID);
				if(resultSet.next()) {
					int id= resultSet.getInt(1);
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
