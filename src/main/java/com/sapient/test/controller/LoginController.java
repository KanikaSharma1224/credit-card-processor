package com.sapient.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.test.error.LoginRequiredException;
import com.sapient.test.model.Users;
import com.sapient.test.oauth.JwtTokenUtil;
import com.sapient.test.service.UsersService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

	@Autowired UsersService service;
	@Autowired JwtTokenUtil util;
	
	@PostMapping("/login")
	public Users login(@RequestBody @Valid Users user) {
		
		if(service.isValidSignInAttempt(user)) {
			Users dbUser = service.getUserDetailbyUserName(user.getUserName());
			String token = util.generateToken(dbUser);
			dbUser.setAccessToken(token);
			return service.save(dbUser);
		}
		throw new LoginRequiredException();
	}
}
