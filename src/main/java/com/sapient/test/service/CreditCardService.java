package com.sapient.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.test.model.CreditCardDetails;
import com.sapient.test.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	CreditCardRepository repo;

	public CreditCardDetails save(CreditCardDetails details) {
		return repo.save(details);
	}

	public List<CreditCardDetails> getAllCards() {
		return repo.findAll();
	}

}
