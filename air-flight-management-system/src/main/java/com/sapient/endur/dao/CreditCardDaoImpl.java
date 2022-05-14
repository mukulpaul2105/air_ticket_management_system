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
import com.sapient.endur.model.CreditCardDetails;
import com.sapient.endur.util.MySQLConnection;

public class CreditCardDaoImpl implements CrediCardDao{
	private Logger logger= Logger.getLogger(AirFlightDaoImpl.class);

	@Override
	public Long addCreditCard(CreditCardDetails creditCard) throws SQLException {
		try(
				Connection connection= MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
									connection.prepareStatement(QueryMapper.ADD_CREDITCARD);				
			) {
				preparedStatement.setString(1, creditCard.getProfileId());
				preparedStatement.setLong(2, creditCard.getCardNumber());
				preparedStatement.setString(3, creditCard.getCardType());
				preparedStatement.setInt(4, creditCard.getExpirationMonth());
				preparedStatement.setInt(5, creditCard.getExpirationYear());
				//executeUpdate() can be applied for all DML statements
				//this methods no. of rows affected
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					logger.info("Credit Card added to database");
					return creditCard.getCardNumber();
					
				}else {				
					throw new SQLException("Unable add Credit Cards");
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
	public Long deleteCreditCard(Long cardNumber) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.DELETE_CREDITCARD);
			){
				
				preparedStatement.setLong(1, cardNumber);
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return cardNumber;
				}else {
					throw new SQLException("Unable to delete credit card");
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
	public Long updateCreditCard(CreditCardDetails creditCard) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.UPDATE_FLIGHTS);
			){
			
			preparedStatement.setString(1, creditCard.getProfileId());
			preparedStatement.setString(2, creditCard.getCardType());
			preparedStatement.setInt(3, creditCard.getExpirationMonth());
			preparedStatement.setInt(4, creditCard.getExpirationYear());
			preparedStatement.setLong(5, creditCard.getCardNumber());
				
				int n = preparedStatement.executeUpdate();
				if(n>0) {
					return creditCard.getCardNumber();
				}else {
					throw new SQLException("Unable to update credit cards");
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
	public CreditCardDetails getCreditCardByNumber(Long cardNumber) throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				PreparedStatement preparedStatement= 
								connection.prepareStatement(QueryMapper.GET_CREDICARD_BY_NO);
			){
				
				preparedStatement.setLong(1,cardNumber);
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.next()) {
					CreditCardDetails creditCard=new CreditCardDetails();
					//read from ResultSet object and write into Employee object
					populateCreditCardDetails(creditCard,resultSet);
					logger.info("Returning credit card details: "+ creditCard);
					return creditCard;
				}else {
					logger.warn("Invalid card number");
					throw new SQLException("Invalid card number");
				}
			}catch(SQLException e) {
				logger.error(e);
				throw e;
			}catch(Exception e) {
				logger.error(e);
				throw new SQLException(e);
			}
	}

	private void populateCreditCardDetails(CreditCardDetails creditCard, ResultSet resultSet) throws SQLException {
		creditCard.setProfileId(resultSet.getString("profile_id"));
		creditCard.setCardNumber(resultSet.getLong("card_number"));
		creditCard.setCardType(resultSet.getString("card_type"));
		creditCard.setExpirationMonth(resultSet.getInt("expiration_month"));
		creditCard.setExpirationYear(resultSet.getInt("expiration_year"));
		
	}

	@Override
	public List<CreditCardDetails> getAllCreditCard() throws SQLException {
		try(
				Connection connection=MySQLConnection.getConnection();	
				Statement statement= connection.createStatement();
			){
				ResultSet resultSet= statement.executeQuery(QueryMapper.GET_ALL_CREDITCARD);
				List<CreditCardDetails> creditCardList= new ArrayList<>();
				while(resultSet.next()) {
					CreditCardDetails creditCard=new CreditCardDetails();
					
					populateCreditCardDetails(creditCard,resultSet);
					creditCardList.add(creditCard);				
				}
				if(creditCardList.size()!=0) {
					logger.info("returning list of credit cards");
					return creditCardList;
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
