package com.sapient.test.oauth;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sapient.test.error.LoginRequiredException;
import com.sapient.test.model.Users;
import com.sapient.test.service.UsersService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
@Order(1)
public class JwtRequestFilter implements Filter {

	public static String USERID_TOKEN = "userId";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	UsersService userService;

	final static Logger logger = getLogger(JwtRequestFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if(!req.getRequestURI().endsWith("login")) {
			final String requestTokenHeader = req.getHeader("Authorization");

			String username = null;
			String jwtToken = null;
			boolean failed = false;
			// JWT Token is in the form "Bearer token". Remove Bearer word and get
			// only the Token
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = jwtTokenUtil.getEmailFromToken(jwtToken);

					logger.info("email token from db " + username);
				} catch (IllegalArgumentException | ExpiredJwtException | SignatureException e) {
					logger.error("Unable to read JWT Token");
					failed=true;
				}
			} else {
				logger.error("JWT Token does not begin with Bearer String");
				failed = true;
			}

			// Once we get the token validate it.
			if (username != null) {

				Users userDetails = this.userService.getUserDetailbyUserName(username);
				logger.info("user token from db " + userDetails.getUserName());
				logger.info("user token from db " + userDetails.getAccessToken());
				// if token is valid configure Spring Security to manually set
				// authentication
				if (userDetails != null) {
					ThreadContext.put(USERID_TOKEN, userDetails.getUserName());
				} else {
					failed = true;
					
				}
			} else
				failed = true;
			if(failed)
				res.sendError(401, "Invalid access token");
		}
		
		chain.doFilter(request, response);

	}

}
