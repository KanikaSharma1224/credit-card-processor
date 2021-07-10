package com.sapient.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.test.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	CreditCardRepository repo;
	
}
