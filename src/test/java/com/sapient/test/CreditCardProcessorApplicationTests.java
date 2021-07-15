package com.sapient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.test.error.ErrorHandler.ApiError;
import com.sapient.test.model.CreditCardDetails;
import com.sapient.test.model.Users;

@AutoConfigureMockMvc
@SpringBootTest
class CreditCardProcessorApplicationTests {

	@Autowired
	MockMvc mvc;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void contextLoads() {
	}

	@Test
	void getAllCardsTest() throws Exception {
		
		this.mvc.perform(get("/api/v1/cards").header("Authorization", getUserAccessToken())).andDo(print()).andExpect(status().isOk());
	}
	
	String getUserAccessToken() throws JsonProcessingException, Exception {
		Users crd = new Users();
		crd.setUserName("Kanika");
		crd.setPassword("Kanika");
		MvcResult result = this.mvc.perform(post("/api/v1/login").content(mapper.writeValueAsString(crd))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
		Users user = mapper.readValue(result.getResponse().getContentAsByteArray(), Users.class);
		return "Bearer "+user.getAccessToken();
	}
	
	String getAdminAccessToken() throws JsonProcessingException, Exception {
		Users crd = new Users();
		crd.setUserName("Admin");
		crd.setPassword("Admin");
		MvcResult result = this.mvc.perform(post("/api/v1/login").content(mapper.writeValueAsString(crd))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
		Users user = mapper.readValue(result.getResponse().getContentAsByteArray(), Users.class);
		return "Bearer "+user.getAccessToken();
				
	}

	@Test
	void addValidCard() throws Exception {
		CreditCardDetails crd = new CreditCardDetails();
		crd.setCardHolderName("Test");
		crd.setCardNumber("4485969992833949");
		this.mvc.perform(post("/api/v1/cards").content(mapper.writeValueAsString(crd)).header("Authorization", getAdminAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	@Test
	void addInvalidCardNumber() throws Exception {
		CreditCardDetails crd = new CreditCardDetails();
		crd.setCardHolderName("Test");
		crd.setCardNumber("448969992833949");
		MvcResult result = this.mvc.perform(post("/api/v1/cards").content(mapper.writeValueAsString(crd)).header("Authorization", getAdminAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isBadRequest()).andReturn();
		ApiError error = mapper.readValue(result.getResponse().getContentAsByteArray(), ApiError.class);
		assertNotNull(error.getErrors());
		assertEquals(1, error.getErrors().size());
		assertEquals("Invalid Credit Card Number", error.getErrors().get("cardNumber"));
	}

	@Test
	void addInvalidCardHolderName() throws Exception {

		CreditCardDetails crd = new CreditCardDetails();
		crd.setCardNumber("4485969992833949");
		MvcResult result = this.mvc.perform(post("/api/v1/cards").content(mapper.writeValueAsString(crd)).header("Authorization", getAdminAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isBadRequest()).andReturn();
		ApiError error = mapper.readValue(result.getResponse().getContentAsByteArray(), ApiError.class);
		assertNotNull(error.getErrors());
		assertEquals(1, error.getErrors().size());
		assertEquals("Card Holder Name can not be empty", error.getErrors().get("cardHolderName"));
	}

}
