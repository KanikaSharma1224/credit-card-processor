package com.sapient.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sapient.test.validations.ValidLuhn10;

@Entity
@Table(name = "credit_card_details")
public class CreditCardDetails {

	@Id
	@ValidLuhn10
	@Column(name = "card_number")
	private String cardNumber;
	
	@NotBlank(message = "Card Holder Name can not be empty")
	@Column(name = "card_holder_name")
	private String cardHolderName;

	@Column(name = "balance")
	private Double balance;
	
	public CreditCardDetails() {
		
	}
	
	public CreditCardDetails(String cNumber, String name, Double balance) {
		this.cardNumber = cNumber;
		this.cardHolderName = name;
		this.balance = balance;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
