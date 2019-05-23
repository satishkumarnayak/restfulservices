package com.demo.restful.restfulservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {
	
	//String message;

	public UserNotFoundException(String message) {
		super(message);
		//this.message = message;
	}
	
	

}
