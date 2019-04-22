package com.restful.web.restproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5505248566038007803L;
	
	public EntityNotFoundException(String message){
		super(message);
	}

}
