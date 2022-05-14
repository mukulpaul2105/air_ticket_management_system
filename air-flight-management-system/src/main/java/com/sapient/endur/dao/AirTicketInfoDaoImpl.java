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
import com.sapient.endur.model.AirTicketInfo;
import com.sapient.endur.util.MySQLConnection;

public class AirTicketInfoDaoImpl implements AirTicketInfoDAO {
	
	private Logger logger= Logger.getLogger(AirTicketInfoDaoImpl.class);

	@Override
	public String addAirTicketInfo(AirTicketInfo airTicketInfo) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.ADD_AIRTICKETINFO);				
			) {
				//replace placeholders with actual values
				preparedStatement.setString(1, airTicketInfo.getTicketId());
				preparedStatement.setString(2, airTicketInfo.getProfileid());
				preparedStatement.setInt(3, airTicketInfo.getFlightid());
				preparedStatement.setDate(4, java.sql.Date.valueOf( airTicketInfo.getFlightDepatureDate()));
				preparedStatement.setString(5, airTicketInfo.getStatus());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info(" AirTicketInfo Details added to database");
					return  airTicketInfo.getTicketId();
				}else {				
					throw new SQLException("Unable add  AirTicketInfo Detials");
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
	public String deleteAirTicketInfo(String ticketId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.DELETE_AIRTICKETINFO);
			){
			
				preparedStatement.setString(1, ticketId);
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return ticketId;
				}else {
					throw new SQLException("Unable to delete TicketId ");
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
	public String updateAirTicketInfo(AirTicketInfo airTicketInfo) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.UPDATE_AIRTICKETINFO);				
			) {
				//replace placeholders with actual values
			preparedStatement.setString(1, airTicketInfo.getProfileid());
			preparedStatement.setInt(2, airTicketInfo.getFlightid());
			preparedStatement.setDate(3, java.sql.Date.valueOf( airTicketInfo.getFlightDepatureDate()));
			preparedStatement.setString(4, airTicketInfo.getStatus());
			preparedStatement.setString(5, airTicketInfo.getTicketId());
			
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("TicketId Details Updated to database");
					return airTicketInfo.getTicketId();
				}else {				
					throw new SQLException("Unable Update TicketId Detials");
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
	public AirTicketInfo getAirTicketInfoByTicketId(String ticketId) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_AIRTICKETINFO_BY_ID);
			){
			
				preparedStatement.setString(1, ticketId);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					AirTicketInfo ticketInfo = new AirTicketInfo();
					//read from ResultSet object and write into Employee object
					populateAirTicketInfo(ticketInfo, resultSet);
					logger.info("Returning Ticket details: "+ticketInfo );
					return ticketInfo ;
				}else {
					logger.warn("Invalid Ticket ID");
					throw new SQLException("Invalid Ticket ID");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}		
	
	}

	private void populateAirTicketInfo(AirTicketInfo ticketInfo, ResultSet resultSet) throws SQLException,Exception {
		ticketInfo.setTicketId(resultSet.getString("ticket_id"));
		ticketInfo.setProfileid(resultSet.getString("profile_id"));
		ticketInfo.setFlightid(resultSet.getInt("flight_id"));
		ticketInfo.setFlightDepatureDate(resultSet.getDate("flight_departure_date").toLocalDate());
		ticketInfo.setStatus(resultSet.getString("status"));
	}
	
	@Override
	public List<AirTicketInfo> getAllAirTicketInfoByTicketId() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_AIRTICKETINFO);
				List<AirTicketInfo> ticketInfoList = new ArrayList<>();
				while(resultSet.next()) {
					AirTicketInfo ticketInfo = new AirTicketInfo ();
				
					populateAirTicketInfo(ticketInfo,resultSet);
					ticketInfoList.add(ticketInfo);				
				}
				if(ticketInfoList.size()!=0) {
					logger.info("returning list of all Ticket Details");
					return ticketInfoList;
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
	public String getLatestAirTicket() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_LATEST_TICKET_ID);
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
