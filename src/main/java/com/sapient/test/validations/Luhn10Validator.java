package com.sapient.test.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luhn10Validator implements ConstraintValidator<ValidLuhn10, String> {

	Logger logger = LoggerFactory.getLogger(Luhn10Validator.class);
	
	@Override
	public boolean isValid(String cardNo, ConstraintValidatorContext context) {
		
		logger.debug("Inside Luhn10 validator ");
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
	    boolean result = (cardSum % 10 == 0);
	    if(!result)
	    	logger.error("Invalid card number supplied as input");
	    return result;
	}

}
