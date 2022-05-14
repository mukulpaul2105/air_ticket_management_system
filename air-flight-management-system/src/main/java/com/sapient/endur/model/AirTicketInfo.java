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


public class AirTicketInfo {
	private String ticketId;
	private String profileid;
	private Integer flightid;
	private LocalDate flightDepatureDate;
	private String status;
	

}