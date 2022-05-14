package com.sapient.endur.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class AirFlightBooking {
	private Integer bookingId;
	private Integer flightId;
	private LocalDate flightDepatureDate;
	private String bookedBy;
	private Integer ticketsBooked;

}
