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

public class CreditCardDetails {
  private  String  profileId;
  private  Long  cardNumber;
  private  String  cardType;
  private  Integer expirationMonth;
  private  Integer expirationYear;
  



}