package com.sapient.endur.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.sapient.endur.model.AirFlight;
import com.sapient.endur.model.AirFlightBooking;
import com.sapient.endur.model.AirFlightDetails;
import com.sapient.endur.model.AirPassengerProfile;
import com.sapient.endur.model.AirTicketInfo;
import com.sapient.endur.model.CreditCardDetails;
import com.sapient.endur.service.AirFlightBookingException;
import com.sapient.endur.service.AirFlightBookingService;
import com.sapient.endur.service.AirFlightBookingServiceImpl;
import com.sapient.endur.service.AirFlightDetailsException;
import com.sapient.endur.service.AirFlightDetailsService;
import com.sapient.endur.service.AirFlightDetailsServiceImpl;
import com.sapient.endur.service.AirFlightException;
import com.sapient.endur.service.AirFlightService;
import com.sapient.endur.service.AirFlightServiceImpl;
import com.sapient.endur.service.AirPassengerProfileException;
import com.sapient.endur.service.AirPassengerProfileService;
import com.sapient.endur.service.AirPassengerProfileServiceImpl;
import com.sapient.endur.service.AirTicketInfoException;
import com.sapient.endur.service.AirTicketInfoService;
import com.sapient.endur.service.AirTicketInfoServiceImpl;
import com.sapient.endur.service.CreditCardException;
import com.sapient.endur.service.CreditCardService;
import com.sapient.endur.service.CreditCardServiceImpl;


public class AirTicketManagementSystem {

	/*
	 *  Admin part
	 */
	/*
	 *  This is for Air Flight Layer
	 */
	private static  Logger logger = Logger.getLogger(AirTicketManagementSystem.class);
	private static AirFlightService airFlightService= new AirFlightServiceImpl();
	private static Scanner scanner= new Scanner(System.in);
	private static AirFlightServiceImpl airFSI = new AirFlightServiceImpl();


	public void airFlight() {
		while(true) {
			try {
				System.out.println("Enter (a)dd, (d)elete, (u)pdate, (g)et flight or get a(l)l flghts");
				String option= scanner.nextLine();
				switch(option) {
				case "a" :  AirFlight airFlight = getAirFlights();
				Integer flightId= addFlights(airFlight);
				if(flightId!=null) {
					System.out.println(flightId+" added to database");
					logger.info(flightId+" added to database");
				}else {
					System.out.println("Unable to add flights, contains invalid data");
					logger.warn("Unable to add flights, contains invalid data");
				}
				break;
				case "d" :  System.out.println("Enter flightId: ");
				flightId= Integer.parseInt(scanner.nextLine());
				flightId = deleteFlights(flightId);
				if(flightId!=null) {
					System.out.println(flightId+" deleted from database");
					logger.info(flightId+" deleted from database");
				}else {
					System.out.println("Unable to delete flights");
					logger.warn("Unable to delete flights");
				}

				break;
				case "u" :  System.out.println("Enter flightId of the flight to update:  ");
				flightId= Integer.parseInt(scanner.nextLine());
				airFlight = airFlightService.getAirFlightById(flightId);								
				flightId= updateFlights(airFlight);

				if(flightId!=null) {
					System.out.println(flightId+" updated to database");
					logger.info(flightId+" updated to database");
				}
				break;
				case "g" :  System.out.println("Enter flightId: ");
				flightId= Integer.parseInt(scanner.nextLine());
				airFlight = getAirFlightById(flightId);
				System.out.println(airFlight);
				logger.info("Fetched "+flightId+" details");
				break;
				case "l" :  List<AirFlight> airflightList= getAllFlights();
				airflightList.stream().forEach(System.out::println);
				logger.info("Fetched all flight records");
				break;

				default :   System.out.println("Invalid option");
				break;
				}
			}catch(AirFlightException e) {
				logger.error(e.getMessage(),e);
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
			}

			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}

	}

	private static List<AirFlight> getAllFlights() throws  AirFlightException {
		return airFlightService.getAllFlights();
	}

	private static AirFlight getAirFlightById(Integer flightId) throws AirFlightException {
		return airFlightService.getAirFlightById(flightId);
	}

	private static Integer updateFlights(AirFlight airflight) throws AirFlightException {
		System.out.println("Update flightId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new flightId: ");
			airflight.setFlightId(Integer.parseInt(scanner.nextLine()));
		}

		System.out.println("Update airlineId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new airlineId: ");
			airflight.setAirlineId(scanner.nextLine());
		}

		System.out.println("Update airlineName ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new airlineName ");
			airflight.setAirlineName(scanner.nextLine());
		}

		System.out.println("Update fromLocation ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new fromLocation ");
			airflight.setFromLocation(scanner.nextLine());
		}

		System.out.println("Update toLocation ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new toLocation ");
			airflight.setToLocation(scanner.nextLine());
		}

		System.out.println("Change departureTime ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new departureTime: ");
			airflight.setDepartureTime(LocalTime.parse(scanner.nextLine()));
		}

		System.out.println("Change arrivalTime ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new arrivalTime: ");
			airflight.setArrivalTime(LocalTime.parse(scanner.nextLine()));
		}

		System.out.println("Change duration ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new duration : ");
			airflight.setDuration(LocalTime.parse(scanner.nextLine()));
		}

		System.out.println("Change totalSeats ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new totalSeats: ");
			airflight.setTotalSeats(Integer.parseInt(scanner.nextLine()));
		}

		return airFlightService.updateFlights(airflight);
	}

	private static Integer deleteFlights(Integer flightId) throws AirFlightException {
		return airFlightService.deleteFlights(flightId);
	}

	private static Integer addFlights(AirFlight airflight) throws AirFlightException {
		return airFSI.addFlights(airflight);
	}

	private static AirFlight getAirFlights (){

		AirFlight airFlight = new AirFlight();
		System.out.println("Enter flightId");

		airFlight.setFlightId(Integer.parseInt(scanner.nextLine()));
		System.out.println("Enter airlineId: ");
		airFlight.setAirlineId(scanner.nextLine());

		System.out.println("Enter new airlineName ");
		airFlight.setAirlineName(scanner.nextLine());

		System.out.println("Enter new fromLocation ");
		airFlight.setFromLocation(scanner.nextLine());

		System.out.println("Enter new toLocation ");
		airFlight.setToLocation(scanner.nextLine());

		System.out.println("Enter new departureTime: ");
		airFlight.setDepartureTime(LocalTime.parse(scanner.nextLine()));

		System.out.println("Enter new arrivalTime: ");
		airFlight.setArrivalTime(LocalTime.parse(scanner.nextLine()));

		System.out.println("Enter new duration : ");
		airFlight.setDuration(LocalTime.parse(scanner.nextLine()));

		System.out.println("Enter new totalSeats: ");
		airFlight.setTotalSeats(Integer.parseInt(scanner.nextLine()));

		return airFlight;
	}


	/*
	 *  This is for Air Flight Details Layer
	 */
	private static AirFlightDetailsService airFlightDetailsService= new AirFlightDetailsServiceImpl();

	public void airFlightDetials() {
		while(true) {
			try {
				System.out.println("Enter (a)dd, (d)elete, (u)pdate, (g)et flight details or get a(l)l flght details (ut)");
				String option= scanner.nextLine();
				switch(option) {
				case "a" :  AirFlightDetails airFlightDetails = getAirFlightDetails();
				Integer flightId= addFlights(airFlightDetails);

				if(flightId!=null) {
					System.out.println(flightId+" added to database");
					logger.info(flightId+" added to database");
				}else {
					System.out.println("Unable to add flight details, contains invalid data");
					logger.warn("Unable to add flight details, contains invalid data");
				}
				break;
				case "d" :  System.out.println("Enter flightId: ");
				flightId= Integer.parseInt(scanner.nextLine());

				System.out.println("Enter flightDepartureDate: (yyyy-mm-dd)");
				String date = scanner.nextLine();
				LocalDate flightDepartureDate = LocalDate.parse(date);
				flightId = deleteFlights(flightId, flightDepartureDate);
				if(flightId!=null) {
					System.out.println(flightId+" deleted from database");
					logger.info(flightId+" deleted from database");
				}else {
					System.out.println("Unable to delete flight details");
					logger.warn("Unable to delete flight details");
				}

				break;
				case "u" :  System.out.println("Enter flightId of the flight to update:  ");
				flightId= Integer.parseInt(scanner.nextLine());

				System.out.println("Enter flightDepartureDate: (yyyy-mm-dd)");
				date = scanner.nextLine();
				flightDepartureDate = LocalDate.parse(date);
				airFlightDetails = airFlightDetailsService.getAirFlightDetailsById(flightId, flightDepartureDate);								
				flightId= updateFlights(airFlightDetails);

				if(flightId!=null) {
					System.out.println(flightId+" updated to database");
					logger.info(flightId+" updated to database");
				}
				break;

				case "g" :  System.out.println("Enter flightId: ");
				flightId= Integer.parseInt(scanner.nextLine());

				System.out.println("Enter flightDepartureDate: (yyyy-mm-dd)");
				date = scanner.nextLine();
				flightDepartureDate = LocalDate.parse(date);
				airFlightDetails = getAirFlightDetailsById(flightId, flightDepartureDate);
				System.out.println(airFlightDetails);
				logger.info("Fetched "+flightId+" details");
				break;

				case "l" :  List<AirFlightDetails> airflightDetailsList= getAllFlightsDetails();
				airflightDetailsList.stream().forEach(System.out::println);
				logger.info("Fetched all flight records");
				break;
				case "ut" :  System.out.println("Enter fd_id:  ");
				flightId= Integer.parseInt(scanner.nextLine());

				airFlightDetails = getAirFlightDetailsByFdId(flightId);

				flightId= updateFlightAT(airFlightDetails);

				if(flightId!=null) {
					System.out.println(flightId+" updated to database");
					logger.info(flightId+" updated to database");
				}
				break;


				default :   System.out.println("Invalid option");
				break;
				}

			} catch(AirFlightDetailsException e) {
				logger.error(e.getMessage(),e);
			} catch(Exception e) {	
				logger.error(e.getMessage(),e);
			}

			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}

	}




	private static Integer updateFlightAT(AirFlightDetails airFlightDetails) throws AirFlightDetailsException {
		System.out.println("Enter new availableSeats ");
		airFlightDetails.setAvailableSeats(Integer.parseInt(scanner.nextLine()));
	

		return airFlightDetailsService.updateAvailableTickets(airFlightDetails);
	}

	private static AirFlightDetails getAirFlightDetailsByFdId(Integer flightId) throws AirFlightDetailsException {
		return airFlightDetailsService.getAirFlightDetailsByFdId(flightId);
	}

	


	private static List<AirFlightDetails> getAllFlightsDetails() throws  AirFlightDetailsException {
		return airFlightDetailsService.getAirFlightDetails();
	}

	private static AirFlightDetails getAirFlightDetailsById(Integer flightId, LocalDate flightDepartureDate) throws AirFlightDetailsException {
		return airFlightDetailsService.getAirFlightDetailsById(flightId, flightDepartureDate);
	}

	private static Integer updateFlights(AirFlightDetails airflightDetails) throws AirFlightDetailsException {
		System.out.println("Update flightId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new flightId: ");
			airflightDetails.setFlightId(Integer.parseInt(scanner.nextLine()));
		}

		System.out.println("Update flight departure date ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new flight departure date: ");
			airflightDetails.setFlightDepatureDate(LocalDate.parse(scanner.nextLine()));
		}

		System.out.println("Update price ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new price ");
			airflightDetails.setPricePerSeat(Double.parseDouble(scanner.nextLine()));
		}

		System.out.println("Update availableSeats ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new availableSeats ");
			airflightDetails.setAvailableSeats(Integer.parseInt(scanner.nextLine()));
		}

		return airFlightDetailsService.updateAirFlightDetails(airflightDetails);
	}

	private static Integer deleteFlights(Integer flightId, LocalDate flightDepartureDate) throws AirFlightDetailsException {
		return airFlightDetailsService.deleteAirFlightDetails(flightId, flightDepartureDate);
	}

	private static Integer addFlights(AirFlightDetails airflightDetails) throws AirFlightDetailsException {
		return airFlightDetailsService.addAirFlightDetails(airflightDetails);
	}

	private static AirFlightDetails getAirFlightDetails (){

		AirFlightDetails airFlightDetails = new AirFlightDetails();
		System.out.println("Enter flightId");

		airFlightDetails.setFlightId(Integer.parseInt(scanner.nextLine()));
		System.out.println("Enter flight departure date: ");
		airFlightDetails.setFlightDepatureDate(LocalDate.parse(scanner.nextLine()));

		System.out.println("Enter new price ");
		airFlightDetails.setPricePerSeat(Double.parseDouble(scanner.nextLine()));

		System.out.println("Enter new available seats ");
		airFlightDetails.setAvailableSeats(Integer.parseInt(scanner.nextLine()));

		return airFlightDetails;
	}


	/*
	 *  This is for Air Passenger Profile Layer
	 */
	private static AirPassengerProfileService passengerProfileService = new AirPassengerProfileServiceImpl();

	public void airPassengerProfile() {
		while(true) {
			try {
				System.out.println("Enter (d)elete, (u)pdate, (g)et Passenger Profile or get a(l)l Passenger Profiles");
				String option= scanner.nextLine();
				switch(option) {
				case "d" : AirPassengerProfile passengerProfile = addPassengerDetails();
				String profileId = addPassengers(passengerProfile);
				System.out.println("Enter profileId: ");
				profileId = scanner.nextLine();
				profileId  = deletePassengerProfile(profileId);
				if(profileId != null) {
					System.out.println(profileId +" deleted from database");
					logger.info(profileId+" deleted from database");
				}else {
					System.out.println("Unable to delete Passenger Profile");
					logger.warn("Unable to delete Passenger Profile");
				}

				break;
				case "u" :  System.out.println("Enter profileId of the Passenger Profile to update:  ");
				profileId = scanner.nextLine();
				passengerProfile = passengerProfileService.getAirPassengerProfileById(profileId);								
				profileId = updatePassengerProfile(passengerProfile);

				if(profileId != null) {
					System.out.println(profileId +" updated to database");
					logger.info(profileId +" updated to database");
				}
				break;
				case "g" :  System.out.println("Enter profileId: ");
				profileId = scanner.nextLine();
				passengerProfile = getPassengersById(profileId);
				System.out.println(passengerProfile);
				logger.info("Fetched "+ profileId +" details");
				break;
				case "l" :  List<AirPassengerProfile> airflightList= getAllPassengerProfiles();
				airflightList.stream().forEach(System.out::println);
				logger.info("Fetched all Passenger Profile records");
				break;

				default :   System.out.println("Invalid option");
				break;
				}
			}catch(AirPassengerProfileException e) {
				logger.error(e.getMessage(),e);
			}catch(Exception e) {	
				logger.error(e.getMessage(),e);
			}

			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}
	}

	private static List<AirPassengerProfile> getAllPassengerProfiles() throws AirPassengerProfileException {
		return passengerProfileService.getAirPassengerProfiles();
	}

	private static AirPassengerProfile getPassengersById(String profileId) throws AirPassengerProfileException {
		return passengerProfileService.getAirPassengerProfileById(profileId);
	}

	private static String updatePassengerProfile(AirPassengerProfile passengerProfile) throws AirPassengerProfileException {

		System.out.println("Update Password ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new Password: ");
			passengerProfile.setPassword(scanner.nextLine());
		}

		System.out.println("Update First Name ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new Fist Name: ");
			passengerProfile.setFirstName(scanner.nextLine());
		}

		System.out.println("Update Last Name ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new Last Name: ");
			passengerProfile.setLastName(scanner.nextLine());
		}

		System.out.println("Update Address ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new address: ");
			passengerProfile.setAddress(scanner.nextLine());
		}

		System.out.println("Change Mobile Number ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new Mobile Number: ");
			passengerProfile.setMobile(Long.parseLong(scanner.nextLine()));
		}

		System.out.println("Change EmailId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new EmailId: ");
			passengerProfile.setEmailId(scanner.nextLine());
		}


		return passengerProfileService.updateAirPassengerProfile(passengerProfile);
	}


	private static String deletePassengerProfile(String profileId) throws AirPassengerProfileException{
		return passengerProfileService.deleteAirPassengerProfile(profileId);
	}

	private static String addPassengers(AirPassengerProfile passengerProfile) throws AirPassengerProfileException {
		return passengerProfileService.addAirPassengerProfile(passengerProfile);
	}

	private static AirPassengerProfile addPassengerDetails() {
		AirPassengerProfile pProfile = new AirPassengerProfile();

		System.out.println("Enter Passenger Profile: ");
		pProfile.setProfileId(scanner.nextLine());

		System.out.println("Enter Password: ");
		pProfile.setPassword(scanner.nextLine());

		System.out.println("Enter Fist Name: ");
		pProfile.setFirstName(scanner.nextLine());

		System.out.println("Enter Last Name: ");
		pProfile.setLastName(scanner.nextLine());

		System.out.println("Enter address: ");
		pProfile.setAddress(scanner.nextLine());

		System.out.println("Enter Mobile Number: ");
		pProfile.setMobile(Long.parseLong(scanner.nextLine()));

		System.out.println("Enter EmailId: ");
		pProfile.setEmailId(scanner.nextLine());

		return pProfile;
	}

	/*
	 * This is for Credit Card Details 
	 */
	private static CreditCardService creditCardService= new CreditCardServiceImpl();

	public void creditCard() {
		while(true) {
			try {
				System.out.println("Enter (d)elete, (u)pdate, (g)et creditcard or get a(l)l creditcard");
				String option= scanner.nextLine();
				switch(option) {
				case "aeeddse" : CreditCardDetails creditCard = addCreditCardDetails();
				Long cardNumber= addCreditCard(creditCard);
				if(cardNumber!=null) {
					System.out.println(cardNumber+" added to database");
					logger.info(cardNumber+" added to database");
				}else {
					System.out.println("Unable to add creditcards, contains invalid data");
					logger.warn("Unable to add creditcards, contains invalid data");
				}
				break;
				case "d" :  System.out.println("Enter card number: ");
				cardNumber= Long.parseLong(scanner.nextLine());
				cardNumber = deleteCreditCard(cardNumber);
				if(cardNumber!=null) {
					System.out.println(cardNumber+" deleted from database");
					logger.info(cardNumber+" deleted from database");
				}else {
					System.out.println("Unable to delete credit cards");
					logger.warn("Unable to delete credit cards");
				}

				break;
				case "u" :  System.out.println("Enter card number of the credit card:  ");
				cardNumber= Long.parseLong(scanner.nextLine());
				creditCard = creditCardService.getCreditCardByNumber(cardNumber);								
				cardNumber= updateCreditCard(creditCard);

				if(cardNumber!=null) {
					System.out.println(cardNumber+" updated to database");
					logger.info(cardNumber+" updated to database");
				}
				break;
				case "g" :  System.out.println("Enter cardNumber: ");
				cardNumber= Long.parseLong(scanner.nextLine());
				creditCard = creditCardService.getCreditCardByNumber(cardNumber);
				System.out.println(creditCard);
				logger.info("Fetched "+cardNumber+" details");
				break;
				case "l" :  List<CreditCardDetails> creditCardList= getAllCreditCard();
				creditCardList.stream().forEach(System.out::println);
				logger.info("Fetched all credit card records");
				break;

				default :   System.out.println("Invalid option");
				break;
				}
			}catch(CreditCardException e) {
				logger.error(e.getMessage(),e);
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
			}

			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}
	}

	private static List<CreditCardDetails> getAllCreditCard() throws  CreditCardException {
		return creditCardService.getAllCreditCard();
	}

	private static Long updateCreditCard(CreditCardDetails creditCard) throws CreditCardException {
		System.out.println("Update profile id ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new profile id: ");
			creditCard.setProfileId(scanner.nextLine());
		}

		System.out.println("Update card number? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new card number: ");
			creditCard.setCardNumber(Long.parseLong(scanner.nextLine()));
		}

		System.out.println("Update card type ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new cardtype ");
			creditCard.setCardType(scanner.nextLine());
		}

		System.out.println("Update expiration month ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new expiration month ");
			creditCard.setExpirationMonth(Integer.parseInt(scanner.nextLine()));
		}

		System.out.println("Update expiration year? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new expiration year ");
			creditCard.setExpirationYear(Integer.parseInt(scanner.nextLine()));
		}



		return creditCardService.updateCreditCard(creditCard);
	}

	private static Long deleteCreditCard(Long cardNumber) throws CreditCardException {
		return creditCardService.deleteCreditCard(cardNumber);
	}

	private static Long addCreditCard(CreditCardDetails creditCard) throws CreditCardException {
		return creditCardService.addCreditCard(creditCard);
	}

	private static CreditCardDetails addCreditCardDetails (){

		CreditCardDetails creditCard = new CreditCardDetails();
		System.out.println("Enter profile id");

		creditCard.setProfileId(scanner.nextLine());
		System.out.println("Enter card number: ");
		creditCard.setCardNumber(Long.parseLong(scanner.nextLine()));

		System.out.println("Enter new card type ");
		creditCard.setCardType(scanner.nextLine());

		System.out.println("Enter new expiration month");
		creditCard.setExpirationMonth(Integer.parseInt(scanner.nextLine()));

		System.out.println("Enter new expiration year ");
		creditCard.setExpirationYear(Integer.parseInt(scanner.nextLine()));



		return creditCard;
	}

	/*
	 *  This is for Air Flight Booking Layer
	 */
	private static AirFlightBookingService airFlightBookingService= new AirFlightBookingServiceImpl();

	public void airFlightBooking() {
		while(true) {
			try {
				AirTicketManagementSystem amsAFB = new AirTicketManagementSystem();
				
				System.out.println("Enter (d)elete, (u)pdate, (g)et flight bookings or get a(l)l flght bookings");
				String option= scanner.nextLine();
				switch(option) {
				case "addffd" :  AirFlightBooking airFlightBooking = addAirFlightBookingDetails();
				Integer bookingId= addAirFlightBooking(airFlightBooking);
				if(bookingId!=null) {
					System.out.println(bookingId+" added to database");
					logger.info(bookingId+" added to database");
				}else {
					System.out.println("Unable to add Bookings, contains invalid data");
					logger.warn("Unable to add flights, contains invalid data");
				}
				break;
				case "d" :  System.out.println("Enter BookingId: ");
				bookingId= Integer.parseInt(scanner.nextLine());
				bookingId = deleteAirFlightBooking(bookingId);
				if(bookingId!=null) {
					System.out.println(bookingId+" deleted from database");
					logger.info(bookingId+" deleted from database");
				}else {
					System.out.println("Unable to delete Booking");
					logger.warn("Unable to delete Booking");
				}
				break;
				case "u" :  System.out.println("Enter BookingId of the flight to update:  ");
				bookingId= Integer.parseInt(scanner.nextLine());
				airFlightBooking = airFlightBookingService.getAirFlightBookingById(bookingId);								
				bookingId= updateAirFlightBooking(airFlightBooking);

				if( bookingId!=null) {
					System.out.println( bookingId+" updated to database");
					logger.info( bookingId+" updated to database");
				}
				break;
				case "g" :  System.out.println("Enter BookingId: ");
				bookingId= Integer.parseInt(scanner.nextLine());
				airFlightBooking = getAirFlightBookingById(bookingId);
				System.out.println(airFlightBooking);
				logger.info("Fetched "+bookingId+" details");
				break;
				case "l" :  List<AirFlightBooking> airflightBookingList= getAirFlightBookings();
				airflightBookingList.stream().forEach(System.out::println);
				logger.info("Fetched all flightBooking records");
				break;

				default :   System.out.println("Invalid option");
				break;
				}
			}catch(AirFlightBookingException e) {
				logger.error(e.getMessage(),e);
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
			}
			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}
	}

	private static List<AirFlightBooking> getAirFlightBookings() throws AirFlightBookingException {
		return airFlightBookingService.getAirFlightBookings();
	}
	private static AirFlightBooking getAirFlightBookingById(Integer bookingId) throws AirFlightBookingException{
		return airFlightBookingService.getAirFlightBookingById(bookingId);
	}
	private static  Integer updateAirFlightBooking(AirFlightBooking airFlightBooking) throws AirFlightBookingException {
		System.out.println("Update BookingId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new BookingId: ");
			airFlightBooking.setBookingId(Integer.parseInt(scanner.nextLine()));
		}

		System.out.println("Update flightId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new flightId: ");
			airFlightBooking.setFlightId(Integer.parseInt(scanner.nextLine()));
		}

		System.out.println("Change departureDate ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new departureDate: ");
			airFlightBooking.setFlightDepatureDate(LocalDate.parse((scanner.nextLine())));
		}


		System.out.println("Change personBookedBy ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new personBookedBy : ");
			airFlightBooking.setBookedBy(scanner.nextLine());
		}


		System.out.println("Change ticketsBooked ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new numberOfTicketsBooked: ");
			airFlightBooking.setTicketsBooked(Integer.parseInt(scanner.nextLine()));
		}
		return airFlightBookingService.updateAirFlightBooking(airFlightBooking);
	}
	private static  Integer deleteAirFlightBooking(int bookingId) throws AirFlightBookingException {
		return airFlightBookingService.deleteAirFlightBooking(bookingId);
	}

	private static Integer addAirFlightBooking(AirFlightBooking flightBooking) throws AirFlightBookingException{
		return airFlightBookingService.addAirFlightBooking(flightBooking);
	}
	private static AirFlightBooking addAirFlightBookingDetails (){

		AirFlightBooking airFlightBooking = new AirFlightBooking();
		System.out.println("Enter bookingId");
		airFlightBooking.setBookingId(Integer.parseInt(scanner.nextLine()));

		System.out.println("Enter flightId");
		airFlightBooking.setFlightId(Integer.parseInt(scanner.nextLine()));

		System.out.println("Enter new filightDepatureDate ");
		airFlightBooking.setFlightDepatureDate(LocalDate.parse(scanner.nextLine()));

		System.out.println("Enter the person booked by: ");
		airFlightBooking.setBookedBy(scanner.nextLine());;

		System.out.println("Enter numberof tickets booked");
		airFlightBooking.setTicketsBooked(Integer.parseInt(scanner.nextLine()));


		return airFlightBooking ;
	}



	/* 
	 *  This is for Air Ticket Info Layer
	 */
	private static AirTicketInfoService airTicketInfoService= new AirTicketInfoServiceImpl();
	public void airTicketInfo() {
		while(true) {
			try {
				System.out.println("Enter (d)elete, (u)pdate, (g)et ticket info or get a(l)l ticket info");
				String option= scanner.nextLine();
				switch(option) {

				case "d" : AirTicketInfo airTicketInfo = addAirTicketInfoDetails();
				String ticketId= addAirTicketInfo(airTicketInfo);
				System.out.println("Enter ticketId: ");
				ticketId= scanner.nextLine();
				ticketId = deleteAirTicketInfo(ticketId);
				if(ticketId!=null) {
					System.out.println(ticketId+" deleted from database");
					logger.info(ticketId+" deleted from database");
				}else {
					System.out.println("Unable to delete ticketinfo");
					logger.warn("Unable to delete ticketinfo");
				}

				break;
				case "u" :  System.out.println("Enter ticketId of the flight to update:  ");
				ticketId= scanner.nextLine();
				airTicketInfo = airTicketInfoService.getAirTicketInfoByTicketId(ticketId);									
				ticketId= updateAirTicketInfo(airTicketInfo);

				if(ticketId!=null) {
					System.out.println(ticketId+" updated to database");
					logger.info(ticketId+" updated to database");
				}
				break;
				case "g" :  System.out.println("Enter ticketId: ");
				ticketId=scanner.nextLine();
				airTicketInfo = getAirTicketInfoByTicketId(ticketId);
				System.out.println(airTicketInfo);
				logger.info("Fetched "+ticketId+" details");
				break;
				case "l" :  List<AirTicketInfo> airTicketInfoList= getAllAirTicketInfoByTicketId();
				airTicketInfoList.stream().forEach(System.out::println);
				logger.info("Fetched all ticketsm records");
				break;

				default :   System.out.println("Invalid option");
				break;
				}
			}catch(AirTicketInfoException e) {
				logger.error(e.getMessage(),e);
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
			}

			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}
	}


	private static AirTicketInfo addAirTicketInfoDetails() {
		AirTicketInfo airTicketInfo = new AirTicketInfo();
		airTicketInfo.setTicketId(ticketIdC);

		airTicketInfo.setProfileid(profileIdC);;

		airTicketInfo.setFlightid(flightIdC);;

		airTicketInfo.setFlightDepatureDate(dpDateC);

		airTicketInfo.setStatus(statusC);

		return airTicketInfo;
	}

	private static List<AirTicketInfo> getAllAirTicketInfoByTicketId() throws  AirTicketInfoException {
		return airTicketInfoService.getAllAirTicketInfoByTicketId();
	}


	private static AirTicketInfo getAirTicketInfoByTicketId(String ticketId) throws AirTicketInfoException {
		return airTicketInfoService.getAirTicketInfoByTicketId(ticketId);
	}
	private static String updateAirTicketInfo(AirTicketInfo airTicketInfo) throws AirTicketInfoException {
		System.out.println("Update ticketId ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new  ticketId: ");
			airTicketInfo.setTicketId(scanner.nextLine());
		}

		System.out.println("Update profileid ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new profileid: ");
			airTicketInfo.setProfileid(scanner.nextLine());
		}

		System.out.println("Update  flightid ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new  flightid ");
			airTicketInfo.setFlightid(Integer.parseInt(scanner.nextLine()));
		}

		System.out.println("Update flightDepatureDate ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new flightDepatureDate ");
			airTicketInfo.setFlightDepatureDate(LocalDate.parse(scanner.nextLine()));
		}

		System.out.println("Update status ? (y/n): ");
		if(scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new status ");
			airTicketInfo.setStatus(scanner.nextLine());
		}


		return  airTicketInfoService.updateAirTicketInfo(airTicketInfo);
	}


	private static String deleteAirTicketInfo(String ticketId) throws  AirTicketInfoException {
		return airTicketInfoService.deleteAirTicketInfo(ticketId);
	}

	private static  String addAirTicketInfo(AirTicketInfo airTicketInfo) throws AirTicketInfoException {
		return airTicketInfoService.addAirTicketInfo(airTicketInfo);
	}



	//================================================================================================================

	/*
	 *  From here its for Customer 
	 */

	private static List<AirFlight> getAirFlightsC(String source, String destination) throws AirFlightException {
		return airFlightService.getFlights(source, destination);
	}


	private static CreditCardDetails getCreditCardByNumber(Long cardNumberC) throws CreditCardException {
		return creditCardService.getCreditCardByNumber(cardNumberC);
	}

	private static String profileIdC = "PFL007";
	private static Integer flightIdC = 1262;
	private static LocalDate dpDateC = LocalDate.parse("2013-05-20");
	private static Long cardNumberC = 4124459047784138L;
	private static String ticketIdC = "TKT030";
	private static String statusC = "BOOKED";
	private static String cardtypeC = "ct";
	private static Integer expMonthC = 3;
	private static Integer expYearC = 2024;
	private static String passwordC = "PFL889";
	private static String fNameC = "jj";
	private static String lNameC = "kk";
	private static String addressC = "kkkd kkdkd 929";
	private static Long mobileC = 3833L;
	private static String emailC = "djkdja";
	private static Integer ticketBookedC = 0;

	public static void main(String[] args) 
			throws AirTicketInfoException, AirFlightException, AirFlightDetailsException, AirPassengerProfileException, CreditCardException {
		AirTicketManagementSystem ams = new AirTicketManagementSystem();

		while(true) { 
			try {
				System.out.println("Enter (a)ddmin, (c)ustomer");
				String option= scanner.nextLine();
				switch(option) {
				/*
				 * For admin
				 */
				case "a" : System.out.println("Type (1) for Air Flight, (2) for Air Flight Details, (3) for Air Passenger Profile, (4) for Credit Card Details, (5) for Flight Booking and (6) for Air Ticket Info");
				Integer option2 = Integer.parseInt(scanner.nextLine());
				switch(option2) {
				case 1 : ams.airFlight();
				break;
				case 2 : ams.airFlightDetials();
				break;
				case 3 : ams.airPassengerProfile();
				break;
				case 4 : ams.creditCard();
				break;
				case 5 : ams.airFlightBooking();
				break;
				case 6 : ams.airTicketInfo();
				break;

				default : System.out.println("Invalid option");
				break;
				}

				//			System.out.println("Type x for exit, any other character to continue....");
				//			if(scanner.nextLine().equalsIgnoreCase("x")) {
				//				break;
				//			}

				//--------------------------------------------------------------------------------------------------------
				/*
				 *  This is for Customer layer
				 */

				case "c" : System.out.println("Type (b) for flight booking or (g) to get ticket details");
				String option3 = scanner.nextLine();
				switch(option3) {
				case "b" : 
					/*
					 *  here takeing the source and destination address from the Customer
					 */
					System.out.println("Enter Source: ");
					String source = scanner.nextLine();
					System.out.println("Enter Destination: ");
					String destination = scanner.nextLine();

					// Showing the list of selected route to the Customer
					List<AirFlight> airflightLists= getAirFlightsC(source, destination);
					airflightLists.stream().forEach(System.out::println);

					// Customer will select one flight from the list
					System.out.println("Select any flightId from the list ");
					System.out.println("To check flight details and seat availability and price");
					System.out.println("Enter flightId");

					flightIdC = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter Departure Date (YYYY-MM-DD)");
					dpDateC = LocalDate.parse(scanner.nextLine());

					getAirFlightDetailsById(flightIdC, dpDateC);
					
					System.out.println("How many tickets you want to book ?");
					ticketBookedC = Integer.parseInt(scanner.nextLine());
					
					Double ticketPrice = getAirFlightDetailsById(flightIdC, dpDateC).getPricePerSeat() * ticketBookedC;
					System.out.println("Total Ticket Price:  " +  ticketPrice);

					if(getAirFlightDetailsById(flightIdC, dpDateC).getAvailableSeats() >= ticketBookedC) {
						
						while(true) {
							System.out.println("Enter (p) to proceed or (x) to cencel");
							String proc = scanner.nextLine();
							if(proc.equalsIgnoreCase("p")) {
								/*
								 *  If customer is existing one then he can search for his profileId else he have to add his details
								 */
								
								System.out.println("Are you exisiting Customer ?");
								System.out.println("if yes then type (y) otherwise (a) to add.. ");
								String option4 = scanner.nextLine();
								if(option4.equalsIgnoreCase("y")) {
									System.out.println("Enter your profileId");
									profileIdC = scanner.nextLine();
									getPassengersById(profileIdC);

									System.out.println("Type (c) to Confirm your profileId");
									String op1 = scanner.nextLine();
									if(op1.equalsIgnoreCase("c")) {
										/*
										 *  Here Customer can check whether his card details is available or not 
										 *  if yes then he can select that one or he also can add new one
										 */
										for(int i = 2; i <= ticketBookedC; i++) {
											System.out.println("Enter other person's Details..");
											// Here we will get the latest profileId then we will increment by 1
											// and then use it as a new profileId
											System.out.println("Person " + i + "'s details" );
											
											
											
											String s = passengerProfileService.getLatestProfileId();
											String e = s.substring(0, 3);
											Integer f = Integer.parseInt(s.substring(3));
											++f;
											String k = f.toString();
											if(k.length() == 1) {
												e = e + "00" + k;
											} else if(k.length() == 2){
												e = e + "0" + k;
											} else {
												e = e + k;
											}
											
											String profileIdCc = e; 
													
											String passwordCc = profileIdCc;
											System.out.println("Enter your First Name: ");
											String fNameCc = scanner.nextLine();
											System.out.println("Enter your Last Name: ");
											String lNameCc = scanner.nextLine();
											System.out.println("Enter your address: ");
											String addressCc = scanner.nextLine();
											System.out.println("Enter your mobile number: ");
											Long mobileCc = Long.parseLong(scanner.nextLine());
											System.out.println("Enter your emailId: ");
											String emailCc = scanner.nextLine();
											/*
											 * Here creating an Object for passenger profile and send it to the database
											 */
											AirPassengerProfile passengerProfile = addPassengerDetailsC(profileIdCc, passwordCc, fNameCc,lNameCc, addressCc,
													mobileCc, emailCc);
											addPassengers(passengerProfile);
											
										}
										
										
										System.out.println("If you have added card detials earlier..");
										System.out.println("Type (g) to get card details or (a) to add card details");
										String op2 = scanner.nextLine();
										if(op2.equalsIgnoreCase("g")) {
											System.out.println("Enter your card number..");
											cardNumberC = Long.parseLong(scanner.nextLine());
											getCreditCardByNumber(cardNumberC);
											System.out.println("Type (y) to proceed with this card.. ");
											if(scanner.nextLine().equalsIgnoreCase("y")) {
												System.out.println("Type (b) to book the ticket ");
												if(scanner.nextLine().equalsIgnoreCase("b")) {
													/*
													 *  Ticket is generated and the data is passed to the Air Ticket Info table of the database
													 */

													AirTicketInfo airTicketInfoC = addAirTicketInfoDetails(ticketIdCreation(), profileIdC, flightIdC, dpDateC, statusC);
													addAirTicketInfo(airTicketInfoC);
													/*
													 *  Here the data will be sent to the Air Ticket Booking table of the database
													 */
													AirFlightBooking airFlightBookingC = addAirFlightBookingDetailsC(flightIdC, dpDateC, profileIdC, ticketBookedC);
													addAirFlightBooking(airFlightBookingC);

													Integer fdIdC = getAirFlightDetailsById(flightIdC, dpDateC).getFdId();
													AirFlightDetails airFlightDetailsC = getAirFlightDetailsByFdId(fdIdC);
													fdIdC = updateFlightATC(airFlightDetailsC);
													
													System.out.println("Congratulations your ticket is generated");
													getAirTicketInfoByTicketId(ticketIdC);

												}


											} else {
												System.out.println("invalid");
											}


										} else if(scanner.nextLine().equalsIgnoreCase("a")) {
											System.out.println("Enter your card number: ");
											cardNumberC = Long.parseLong(scanner.nextLine());
											System.out.println("Enter card type: ");
											cardtypeC = scanner.nextLine();
											System.out.println("Enter expiration month: ");
											expMonthC = Integer.parseInt(scanner.nextLine());
											System.out.println("Enter expiration year: ");
											expYearC = Integer.parseInt(scanner.nextLine());

											/*
											 *  Here new credit card details will be added 
											 */
											CreditCardDetails creditCard = addCreditCardDetails(profileIdC, cardNumberC, cardtypeC, expMonthC, expYearC);
											addCreditCard(creditCard);

											System.out.println("Type (y) to continue...");
											if(scanner.nextLine().equalsIgnoreCase("y")) {
												System.out.println("Type (b) to book the ticket ");
												if(scanner.nextLine().equalsIgnoreCase("b")) {
													/*
													 *  Ticket is generated and the data is passed to the Air Ticket Info table of the database
													 */

													AirTicketInfo airTicketInfoC = addAirTicketInfoDetails(ticketIdCreation(), profileIdC, flightIdC, dpDateC, statusC);
													addAirTicketInfo(airTicketInfoC);
													/*
													 *  Here the data will be sent to the Air Ticket Booking table of the database
													 */
													AirFlightBooking airFlightBookingC = addAirFlightBookingDetailsC(flightIdC, dpDateC, profileIdC, ticketBookedC);
													addAirFlightBooking(airFlightBookingC);

													Integer fdIdC = getAirFlightDetailsById(flightIdC, dpDateC).getFdId();
													AirFlightDetails airFlightDetailsC = getAirFlightDetailsByFdId(fdIdC);
													fdIdC = updateFlightATC(airFlightDetailsC);
													
													System.out.println("Congratulations your ticket is generated");
													getAirTicketInfoByTicketId(ticketIdC);

												}


											} else {
												System.out.println("invalid");
											}

										} else {
											System.out.println("Invalid character");
										}

									} else {
										System.out.println("Invalid character");
									}

									// This is for adding passenger profile

								} else if(option4.equalsIgnoreCase("a")){
									for(int i = 1; i <= ticketBookedC; i++) {
										System.out.println("Enter " + i + " person's Details..");
										
										String s = passengerProfileService.getLatestProfileId();
										String e = s.substring(0, 3);
										Integer f = Integer.parseInt(s.substring(3));
										++f;
										String k = f.toString();
										if(k.length() != 3) {
											e = e + "0" + k;
										} else {
											e = e + k;
										}
										profileIdC = e; 
										System.out.println("Create a password (ex: PFK8837)");
										passwordC = scanner.nextLine();
										System.out.println("Enter your First Name: ");
										fNameC = scanner.nextLine();
										System.out.println("Enter your Last Name: ");
										lNameC = scanner.nextLine();
										System.out.println("Enter your address: ");
										addressC = scanner.nextLine();
										System.out.println("Enter your mobile number: ");
										mobileC = Long.parseLong(scanner.nextLine());
										System.out.println("Enter your emailId: ");
										emailC = scanner.nextLine();
										/*
										 * Here creating an Object for passenger profile and send it to the database
										 */
										AirPassengerProfile passengerProfile = addPassengerDetailsC(profileIdC, passwordC, fNameC,lNameC, addressC,
												mobileC, emailC);
										addPassengers(passengerProfile);
										
									}

									ticketPrice = getAirFlightDetailsById(flightIdC, dpDateC).getPricePerSeat() * ticketBookedC;
									System.out.println("Total Ticket Price:  " +  ticketPrice);

									/*
									 *  Here Customer can check whether his card details is available or not 
									 *  if yes then he can select that one or he also can add new one
									 */

										System.out.println("Enter your card number: ");
										cardNumberC = Long.parseLong(scanner.nextLine());
										System.out.println("Enter card type: ");
										cardtypeC = scanner.nextLine();
										System.out.println("Enter expiration month: ");
										expMonthC = Integer.parseInt(scanner.nextLine());
										System.out.println("Enter expiration year: ");
										expYearC = Integer.parseInt(scanner.nextLine());

										/*
										 *  Here new credit card details will be added 
										 *  we have to write some code
										 */


										CreditCardDetails creditCard = addCreditCardDetails(profileIdC, cardNumberC, cardtypeC, expMonthC, expYearC);
										addCreditCard(creditCard);

										System.out.println("Type (b) to book the ticket ");
										if(scanner.nextLine().equalsIgnoreCase("b")) {
											/*
											 *  Ticket is generated and the data is passed to the Air Ticket Info table of the database
											 */
											AirTicketInfo airTicketInfoC = addAirTicketInfoDetails(ticketIdCreation(), profileIdC, flightIdC, dpDateC, statusC);
											addAirTicketInfo(airTicketInfoC);
											/*
											 *  Here the data will be sent to the Air Ticket Booking table of the database
											 */


											AirFlightBooking airFlightBookingC = addAirFlightBookingDetailsC(flightIdC, dpDateC, profileIdC, ticketBookedC);
											addAirFlightBooking(airFlightBookingC);
											
											Integer fdIdC = getAirFlightDetailsById(flightIdC, dpDateC).getFdId();
											AirFlightDetails airFlightDetailsC = getAirFlightDetailsByFdId(fdIdC);
											fdIdC = updateFlightATC(airFlightDetailsC);

											System.out.println("Congratulations your ticket is generated");
											getAirTicketInfoByTicketId(ticketIdC);

										} else {
											System.out.println("invalid character");
										}


								} else {
									System.out.println("invalid character");
								}

							} else if(proc.equalsIgnoreCase("x")) {
								break;
							}
						} 
						break;

						
					} else {
						System.out.println("Seat is not Availble...");
					}
					
					
					/*
					 *  Here a customer can get his ticket details 
					 */
				case "g" : 
					System.out.println("Enter ticketId");
					getAirTicketInfoByTicketId(scanner.nextLine());
					break;
				default : System.out.println("invalid option");
				break;
				}
				System.out.println("Type x for exit, any other character to continue..");
				if(scanner.nextLine().equalsIgnoreCase("x")) {
					break;
				}

				}

			} catch(Exception e) {
				logger.error(e.getMessage(),e);
			}
			System.out.println("Type x for exit, any other character to continue..");
			if(scanner.nextLine().equalsIgnoreCase("x")) {
				break;
			}
		}

	}



	private static Integer updateFlightATC(AirFlightDetails airFlightDetailsC) throws AirFlightDetailsException {
		airFlightDetailsC.setAvailableSeats(airFlightDetailsC.getAvailableSeats() - ticketBookedC);
		return airFlightDetailsService.updateAvailableTickets(airFlightDetailsC);
	}

	private static CreditCardDetails addCreditCardDetails(String profileIdC2, Long cardNumberC2, String cardtypeC2,
			Integer expMonthC2, Integer expYearC2) {
		CreditCardDetails creditCardC = new CreditCardDetails();

		creditCardC.setProfileId(profileIdC2);
		creditCardC.setCardNumber(cardNumberC2);
		creditCardC.setCardType(cardtypeC2);
		creditCardC.setExpirationMonth(expMonthC2);
		creditCardC.setExpirationYear(expYearC2);

		return creditCardC;

	}
	

	private static AirFlightBooking addAirFlightBookingDetailsC(Integer flightIdC2, LocalDate dpDateC2,
			String profileIdC2, Integer ticketBookedC2) {
		AirFlightBooking airFlightBookingC = new AirFlightBooking();
		airFlightBookingC.setFlightId(flightIdC2);
		airFlightBookingC.setFlightDepatureDate(dpDateC2);
		airFlightBookingC.setBookedBy(profileIdC2);
		airFlightBookingC.setTicketsBooked(ticketBookedC2);
		return airFlightBookingC ;
	}

	private static AirPassengerProfile addPassengerDetailsC(String profileIdC2, String passwordC2, String fNameC2,
			String lNameC2, String addressC2, Long mobileC2, String emailC2) {
		AirPassengerProfile pProfileC = new AirPassengerProfile();

		pProfileC.setProfileId(profileIdC2);
		pProfileC.setPassword(passwordC2);
		pProfileC.setFirstName(fNameC2);
		pProfileC.setLastName(lNameC2);
		pProfileC.setAddress(addressC2);
		pProfileC.setMobile(mobileC2);
		pProfileC.setEmailId(emailC2);
		return pProfileC;
	}

	private static AirTicketInfo addAirTicketInfoDetails(String ticketIdC2, String profileIdC2, Integer flightIdC2,
			LocalDate dpDateC2, String statusC2) {
		AirTicketInfo airTicketInfoC = new AirTicketInfo();
		airTicketInfoC.setTicketId(ticketIdC);
		airTicketInfoC.setProfileid(profileIdC);;
		airTicketInfoC.setFlightid(flightIdC);;
		airTicketInfoC.setFlightDepatureDate(dpDateC);
		airTicketInfoC.setStatus(statusC);
		return airTicketInfoC;
	}

	public static String ticketIdCreation() throws AirTicketInfoException {
		
		String ticketIdLatest = airTicketInfoService.getLatestAirTicket();
		String e = ticketIdLatest.substring(0, 3);
		Integer f = Integer.parseInt(ticketIdLatest.substring(3));
		++f;
		String k = f.toString();
		if(k.length() == 2) {
			e = e + "0" + k;
		} else if(k.length() == 1) {
			e = e + "00" + k;
		} else {
			e = e + k;
		}
		
		ticketIdLatest = e; 
		
		return ticketIdLatest;
	}

}
