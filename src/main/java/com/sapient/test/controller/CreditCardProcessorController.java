package com.sapient.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.test.model.CreditCardDetails;
import com.sapient.test.service.CreditCardService;

@RestController("/api/v1")
public class CreditCardProcessorController {
	
	
	@Autowired
	CreditCardService service;

	@PostMapping("/cards")
	public void addCard(@RequestBody CreditCardDetails details) {
		
	}
	
	@GetMapping("/cards")
	public List<CreditCardDetails> getAllCards() {
		
		
		return null;
	}
	
}
