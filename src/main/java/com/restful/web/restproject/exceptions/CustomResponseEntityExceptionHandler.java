package com.restful.web.restproject.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restful.web.restproject.model.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(response, HttpStatus.SERVICE_UNAVAILABLE);

	}

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptionException(EntityNotFoundException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);

	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(new Date(),
				"Validate Error", ex.getBindingResult().getAllErrors().toString());


		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}
