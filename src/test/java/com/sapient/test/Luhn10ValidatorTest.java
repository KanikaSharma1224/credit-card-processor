package com.sapient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sapient.test.validations.Luhn10Validator;

public class Luhn10ValidatorTest {

	private static Luhn10Validator validator;
	
	@BeforeAll
	public static void setup() {
		validator=new Luhn10Validator();
	}
	
	@Test
	public void testValid() {
		assertEquals(true,validator.isValid("79927398713", null));
	}
	
	@Test
	public void testInvalid() {
		assertEquals(false,validator.isValid("79928398713", null));
	}
	
}
