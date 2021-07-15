package com.sapient.test.error;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {
	
	public static class ApiError {
		private String timestamp;
		private Map<String,String> errors;
		
		public ApiError() {
			timestamp=LocalDateTime.now().toString();
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public Map<String, String> getErrors() {
			return errors;
		}
		public void setErrors(Map<String, String> errors) {
			this.errors = errors;
		}
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ApiError api=new ApiError();
		api.setErrors(errors);
		return new ResponseEntity<ErrorHandler.ApiError>(api,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginRequiredException.class)
	public ResponseEntity<ApiError> handleLoginRequired(LoginRequiredException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("username/password/access token", "Either the credentials provided are invalid or the access token is invalid or has expired or missing privileges for the operation");
		ApiError api=new ApiError();
		api.setErrors(errors);
		return new ResponseEntity<ErrorHandler.ApiError>(api,HttpStatus.UNAUTHORIZED);
	}

}
