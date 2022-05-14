package com.sapient.endur.dao;

import java.sql.SQLException;
import java.util.List;


import com.sapient.endur.model.CreditCardDetails;

public interface CrediCardDao {
	public abstract Long addCreditCard(CreditCardDetails creditCard) throws SQLException;
	public abstract Long deleteCreditCard(Long cardNumber) throws SQLException;
	public abstract Long updateCreditCard(CreditCardDetails creditCard) throws SQLException;
	public abstract CreditCardDetails getCreditCardByNumber(Long cardNumber) throws SQLException;
	public abstract List<CreditCardDetails> getAllCreditCard() throws SQLException;
}
