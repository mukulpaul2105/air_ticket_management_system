package com.sapient.endur.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.dao.CrediCardDao;
import com.sapient.endur.dao.CreditCardDaoImpl;
import com.sapient.endur.model.CreditCardDetails;

public class CreditCardServiceImpl implements CreditCardService {
	private CrediCardDao creditCardDao= new CreditCardDaoImpl();
	private Logger logger= Logger.getLogger(AirFlightServiceImpl.class);
	private Validator creditCardvalidator = new Validator();
	@Override
	public Long addCreditCard(CreditCardDetails creditCard) throws CreditCardException {
		try {

			if(validateCreditCard(creditCard)) {
				logger.info("creditcard object is valid");
				return creditCardDao.addCreditCard(creditCard);
			}else {
				logger.warn("Invalid creditcard Details");
				return null;
			}
		}catch(SQLException e) {
			throw new CreditCardException(e.getMessage(),e);
		}catch(Exception e) {
			throw new CreditCardException(e.getMessage(),e);
		}

	}
	private boolean validateCreditCard(CreditCardDetails creditCard) {

		if(creditCardvalidator.isValidProfileId(creditCard.getProfileId())) {
			if(creditCardvalidator.isValidCardNumber(creditCard.getCardNumber())) {
				if(creditCardvalidator.isValidCardType(creditCard.getCardType())) {
					if(creditCardvalidator.isValidExpirationMonth(creditCard.getExpirationMonth())) {
						if(creditCardvalidator.isValidExpirationYear(creditCard.getExpirationYear())) {
							logger.info("In service layer, credit card object is valid");
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public Long deleteCreditCard(Long cardNumber) throws CreditCardException {
		try {
			return creditCardDao.deleteCreditCard(cardNumber);	

		}catch(SQLException e) {
			throw new CreditCardException(e.getMessage(),e);
		}catch(Exception e) {
			throw new CreditCardException(e.getMessage(),e);
		}


	}
	@Override
	public Long updateCreditCard(CreditCardDetails creditCard) throws CreditCardException {
		try {
			return creditCardDao.updateCreditCard(creditCard);	

		}catch(SQLException e) {
			throw new CreditCardException(e.getMessage(),e);
		}catch(Exception e) {
			throw new CreditCardException(e.getMessage(),e);
		}


	}
	@Override
	public CreditCardDetails getCreditCardByNumber(Long cardNumber) throws CreditCardException {
		try {
			return creditCardDao.getCreditCardByNumber(cardNumber);	

		}catch(SQLException e) {
			throw new CreditCardException(e.getMessage(),e);
		}catch(Exception e) {
			throw new CreditCardException(e.getMessage(),e);
		}
	}
	@Override
	public List<CreditCardDetails> getAllCreditCard() throws CreditCardException {
		try {
			return creditCardDao.getAllCreditCard();	

		}catch(SQLException e) {
			throw new CreditCardException(e.getMessage(),e);
		}catch(Exception e) {
			throw new CreditCardException(e.getMessage(),e);
		}

	}

}
