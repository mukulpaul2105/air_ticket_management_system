package com.sapient.endur.model;

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
public class AirPassengerProfile {

	private String profileId;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private Long mobile;
	private String emailId;
}