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

public class AirFlightDetails {
	private int fdId;
	private int flightId;
	private LocalDate flightDepatureDate; 
	private Double pricePerSeat;
	private int availableSeats;
	
	

}