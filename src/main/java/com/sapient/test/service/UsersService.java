package com.sapient.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.test.model.Users;
import com.sapient.test.model.Users.APIRole;
import com.sapient.test.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired UsersRepository repo;

	public Users getUserDetailbyUserName(String username) {
		// TODO Auto-generated method stub
		return repo.findByUserName(username);
	}
	
	public Users save (Users user) {
		return repo.save(user);
	}

	public boolean isValidSignInAttempt(Users user) {
		Users dbUser =  getUserDetailbyUserName(user.getUserName());
		if(dbUser==null || !dbUser.getPassword().equals(user.getPassword()))
			return false;
		return true;
	}

	public boolean isAdminUser(String id) {
		Users dbUser =  getUserDetailbyUserName(id);
		System.out.println(dbUser.getUserName()+dbUser.getPassword()+dbUser.getUserRole());
		if(dbUser==null || !dbUser.getUserRole().equals("ADMIN"))
			return false;
		return true;
	}

}
