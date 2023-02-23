package com.tweetapp.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tweetapp.errorResponse.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();

		LOGGER.error(ex.getMessage(), ex);

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimestamp(new Date().getTime());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, null, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TweetNotFoundException.class)
	public ResponseEntity<Object> userNotFound(TweetNotFoundException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();

		LOGGER.error(ex.getMessage(), ex);

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimestamp(new Date().getTime());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, null, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> tweetNotFound(UserNotFoundException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();

		LOGGER.error(ex.getMessage(), ex);

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimestamp(new Date().getTime());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, null, HttpStatus.NOT_FOUND);
	}
}
