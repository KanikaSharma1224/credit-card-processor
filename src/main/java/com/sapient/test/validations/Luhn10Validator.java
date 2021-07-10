package com.sapient.test.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Luhn10Validator implements ConstraintValidator<ValidLuhn10, String> {

	@Override
	public boolean isValid(String cardNo, ConstraintValidatorContext context) {
		
		System.out.println("Inside validator ");
		int cardLength = cardNo.length();
		 
	    int cardSum = 0;
	    boolean isSecond = false;
	    for (int i = cardLength - 1; i >= 0; i--)
	    {
	 
	        int d = cardNo.charAt(i) - '0';
	 
	        if (isSecond == true)
	            d = d * 2;
	 
	        // We add two digits to handle
	        // cases that make two digits
	        // after doubling
	        cardSum += d / 10;
	        cardSum += d % 10;
	 
	        isSecond = !isSecond;
	    }
	    return (cardSum % 10 == 0);
	}

}
