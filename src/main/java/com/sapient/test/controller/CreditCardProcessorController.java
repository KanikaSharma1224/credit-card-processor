package com.sapient.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.test.model.CreditCardDetails;
import com.sapient.test.service.CreditCardService;

@RestController
@RequestMapping(value = "/api/v1")
public class CreditCardProcessorController {

	Logger logger = LoggerFactory.getLogger(CreditCardProcessorController.class);
	
	@Autowired
	CreditCardService service;

	@PostMapping("/cards")
	public ResponseEntity<CreditCardDetails> addCard(@RequestBody @Valid CreditCardDetails details) {
		logger.debug("Inside Add card");
		details.setBalance(0.0);
		CreditCardDetails crd = service.save(details);
		logger.info("New Card successfully added ");
		return new ResponseEntity<CreditCardDetails>(crd,HttpStatus.CREATED);
	}

	@GetMapping("/cards")
	public List<CreditCardDetails> getAllCards() {
		logger.debug("Inside get all cards");
		return service.getAllCards();
	}

}
