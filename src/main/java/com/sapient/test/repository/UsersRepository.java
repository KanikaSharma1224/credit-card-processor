package com.sapient.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.test.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	Users findByUserName(String username);

}
