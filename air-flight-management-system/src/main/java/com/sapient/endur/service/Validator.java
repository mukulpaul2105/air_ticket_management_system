package com.sapient.endur.service;

import java.time.LocalDate;
import java.time.LocalTime;

public class Validator {
	public Boolean isValidAirFlightId(Integer flightId) {
		String regex= "^[0-9]{3,4}$";
		return flightId.toString().matches(regex);
	}
	
	public Boolean isValidAirFlightIdT(Integer flightIdT) {
		String regex= "^[0-9]+$";
		return flightIdT.toString().matches(regex);
	}
	
	
	public Boolean isValidAirlineName(String airlineName) {
		String regex= "^[a-zA-Z\\s.]+$";
		return airlineName.matches(regex);
	}
	public Boolean isValidFromLocation(String fromLocation) {
		String regex= "^[a-zA-Z\\s.]+$";
		return fromLocation.matches(regex);
	}
	public Boolean isValidToLocation(String toLocation) {
		String regex= "^[a-zA-Z\\s.]+$";
		return toLocation.matches(regex);
		
	}
	
	public Boolean isValidDepartureTime(LocalTime departureTime) {		
//		return departureTime.isBefore(LocalTime.now());
		return true;
	}
	
	public Boolean isValidArrivalTime(LocalTime arrivalTime) {		
//		return arrivalTime.isBefore(LocalTime.now());
		return true;
	}
	public Boolean isValidDuration(LocalTime duration) {
		return true;
	}
	
	public Boolean isValidTotalSeats(Integer totalSeats) {
		String regex= "^[0-9]{3}$";
		return totalSeats.toString().matches(regex);
	}
	
	public Boolean isValidProfileId(String profileId) {
		return true;

	}
	
	public Boolean isValidPassword(String password) {
		String regex= "^[a-zA-Z0-9]*$";
		return password.toString().matches(regex);
	}
	
	public Boolean isValidFirstName(String firstName) {
		String regex= "^[a-zA-Z\\s.]+$";
		return firstName.matches(regex);
	}
	
	public Boolean isValidLastName(String lastName) {
		String regex= "^[a-zA-Z\\s.]+$";
		return lastName.matches(regex);
	}
	
	public Boolean isValidAddress(String address) {
//		String regex= "^[A-Za-z0-9.-\\s.]*$";
//		return address.matches(regex);
		return true;
		
	}
	
	public Boolean isValidMobile(Long mobile) {
		String regex= "^[6-9]{1}[0-9]{9}$";
		return mobile.toString().matches(regex);
	}
	
	public Boolean isValidEmail(String emailId) {		
		String regex="^[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+.[a-zA-Z]{2,3}$";
		return emailId.matches(regex);
	}
	
	
	public Boolean isValidFlightId(Integer flightId) {
		String regex= "^[0-9]{3,4}$";
		return flightId.toString().matches(regex);
	}
	
	public Boolean isValidFlightDepatureDate(LocalDate flightDepatureDate) {
		return true;
	}
	
	public Boolean isValidBookedBy(String bookedBy) {
//		String regex= "^[A-Z]{3}[0-9]{4}$";
//		return bookedBy.toString().matches(regex);
		return true;
	}
	
	public Boolean isValidTicketBooked(Integer ticketsBooked) {
		String regex= "^[0-9]{1}$";
		return ticketsBooked.toString().matches(regex);
	}
	
	public Boolean isValidPrice(Double price) {
		if(price <= 0) {
			return false;
		}
		return true;
	}
	

	public Boolean isValidCardNumber(Long cardNumber) {
//		String regex= "^[1-9]{1}[0-9]{15}$";
//		return cardNumber.toString().matches(regex);
		return true;
	}
	
	public Boolean isValidCardType(String cardType) {
//		String regex= "^[a-zA-Z\\s.]+$";
//		return cardType.matches(regex);
		return true;
	}
	public Boolean isValidExpirationMonth(Integer expirationMonth) {
//		if(expirationMonth < 0 || expirationMonth >12) {
//			return false;
//		}
		return true;
	}
	public Boolean isValidExpirationYear(Integer expirationYear) {
//		String regex= "^[2]{1}[0-9]{3}$";
//		return expirationYear.toString().matches(regex);
		return true;
	}
	

	public Boolean isValidTicketId(String ticketId) {
		
		return true;
		
	}
	
	public Boolean isValidProfileid(String profileid) {
		String regex= "^[a-zA-Z[0-9]]+$";
	return profileid.matches(regex);
		//return true;
	}
	public Boolean isValidStatus(String  status) {
		String regex= "^[a-zA-Z\\s.]+$";
	return  status.matches(regex);
		//return true;
	}

	public boolean isValidAirlineId(String airlineId) {
		String regex= "^[0-9]{6}$";
		return airlineId.matches(regex);
	}
	
}