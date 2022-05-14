package com.sapient.endur.model;

import java.time.LocalTime;

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

public class AirFlight {

	private Integer flightId;
	private String airlineId;
	private String airlineName;
	private String fromLocation;
	private String toLocation;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalTime duration;
	private Integer totalSeats;
	
	
}