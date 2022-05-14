package com.sapient.endur.mapper;

public interface QueryMapper {

	
	public static final String ADD_PASSENGER =
			"insert into air_passenger_profile(profile_id, password, first_name, last_name, address, mobile_number, email_id) values(?, ?, ?, ?, ?, ?, ?)";

	public static final String UPDATE_PASSENGER = 
			"update air_passenger_profile set password = ?, first_name = ?, last_name = ?, address = ?, mobile_number = ?, email_id = ? where profile_id = ?";

	public static final String DELETE_PASSENGER = 
			"delete from air_passenger_profile where profile_id = ?";

	public static final String GET_PASSENGER_BY_ID = "select * from air_passenger_profile where profile_id = ?";

	public static final String GET_ALL_PASSENGERS = "select * from air_passenger_profile";
	
	public static final String GET_Latest_PROFILE_ID = "select max(profile_id) from air_passenger_profile";



	public static final String ADD_FLIGHTS= 
			"insert into air_flight( flight_id,airline_id,airline_name,from_location,to_location,departure_time,arrival_time,duration,total_seats) values(?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_FLIGHTS =
			"update air_flight set airline_id=?,airline_name=?,from_location=?,to_location=?,departure_time=?,arrival_time=?,duration=?,total_seats=? where flight_id=?";

	public static final String DELETE_FLIGHTS= "delete from air_flight where  flight_id=?";
	public static final String GET_FLIGHTS_BY_ID= "select * from air_flight where flight_id=?";
	public static final String GET_ALL_FLIGHTS= "select * from air_flight";

	public static final String GET_FLIGHTS = "select * from air_flight where from_location = ? and to_location = ?";

	/*
	 *  QueryMapper for Air Flight Details
	 */
	public static final String ADD_AIR_FLIGHT_DETAILS =
			"insert into air_flight_details(flight_id, flight_departure_date, price_per_seat, available_seats) values(?, ?, ?, ?)";

	public static final String UPDATE_AIR_FLIGHT_DETAILS = 
			"update air_flight_details set price_per_seat = ?, available_seats = ?  where flight_id = ? and flight_departure_date = ?";

	public static final String DELETE_AIR_FLIGHT_DETAILS = 
			"delete from air_flight_details where flight_id = ? and flight_departure_date = ?";

	public static final String GET_AIR_FLIGHT_DETAILS_BY_ID = "select * from air_flight_details where flight_id = ? and flight_departure_date = ?";

	public static final String GET_ALL_AIR_FLIGHT_DETAILS = "select * from air_flight_details";
	
	public static final String GET_FLIGHT_BY_FD_ID = "select * from air_flight_details where fd_id = ?";
	
	public static final String UPDATE_AVAILABLE_SEATS = 
			"update air_flight_details set available_seats = ?  where fd_id = ?";
	

	/*
	 * QuerryMapper For CreditCard Details	
	 */
	public static final String ADD_CREDITCARD= 
			"insert into air_credit_card_details(profile_id,card_number,card_type,expiration_month,expiration_year) values(?,?,?,?,?)";
	public static final String UPDATE_CREDITCARD =
			"update air_credit_card_details set profile_id=?,card_type=?,expiration_month=?,expiration_year=? where card_number=?";
	public static final String DELETE_CREDITCARD= "delete from air_flight where  card_number=?";
	public static final String GET_CREDICARD_BY_NO= "select * from air_credit_card_details where card_number=?";
	public static final String GET_ALL_CREDITCARD= "select * from air_credit_card_details";

	/*
	 * airticketinfo querymaper
	 */
	
	public static final String ADD_AIRTICKETINFO= 
			"insert into air_ticket_info ( ticket_id, profile_id, flight_id, flight_departure_date, status) values(?,?,?,?,?)";
	public static final String UPDATE_AIRTICKETINFO =
			"update air_ticket_info  set profile_id=?, flight_id=?, flight_departure_date=?, status=? where ticket_id = ?";

	public static final String DELETE_AIRTICKETINFO= "delete from air_ticket_info  where ticket_id=?";
	public static final String GET_AIRTICKETINFO_BY_ID= "select * from air_ticket_info  where ticket_id=?";
	public static final String GET_ALL_AIRTICKETINFO= "select * from air_ticket_info ";
	
	public static final String GET_LATEST_TICKET_ID = "select max(ticket_id) from air_ticket_info";
	
	
	

	/*
	 * QueryMapper For Air Flight Booking
	 */
	public static final String ADD_FLIGHT_BOOKING =
			"insert into air_flight_booking(flight_id, flight_departure_date,booked_by, tickets_booked) values(?, ?, ?, ?)";

	public static final String UPDATE_FLIGHT_BOOKING = 
			"update air_flight_booking set flight_id = ?, flight_departure_date = ?, booked_by = ?, tickets_booked = ? where booking_id = ?";

	public static final String DELETE_FLIGHT_BOOKING = "delete from air_flight_booking where booking_id = ?";

	public static final String GET_FLIGHT_BOOKING_BY_ID = "select * from air_flight_booking where booking_id = ?";

	public static final String GET_ALL_FLIGHT_BOOKING = "select * from air_flight_booking";

	public static final String GET_LATEST_ID = "select max(booking_id) from air_flight_booking";
	

}
