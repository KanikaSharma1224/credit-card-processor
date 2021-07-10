package com.sapient.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.test.model.CreditCardDetails;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardDetails, String> {

}
