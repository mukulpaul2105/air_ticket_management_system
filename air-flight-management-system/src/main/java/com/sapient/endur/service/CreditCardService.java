package com.sapient.endur.service;


import java.util.List;

import com.sapient.endur.model.CreditCardDetails;

public interface CreditCardService {
	public abstract Long addCreditCard(CreditCardDetails creditCard) throws CreditCardException;
	public abstract Long deleteCreditCard(Long cardNumber) throws CreditCardException;
	public abstract Long updateCreditCard(CreditCardDetails creditCard) throws CreditCardException;
	public abstract CreditCardDetails getCreditCardByNumber(Long cardNumber) throws CreditCardException;
	public abstract List<CreditCardDetails> getAllCreditCard() throws CreditCardException;
}
