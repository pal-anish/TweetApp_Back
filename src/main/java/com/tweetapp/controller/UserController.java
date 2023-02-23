package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.facade.UsersFacade;

@CrossOrigin(origins = "http://ec2-18-212-55-150.compute-1.amazonaws.com:3000")
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UsersFacade usersfacade;

	@GetMapping(path = "/users/all")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<?> getALlUsers() throws UserNotFoundException {
		return ResponseEntity.ok(usersfacade.getAllUsers());
	}

	@GetMapping(path = "user/search/{username}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<?> searchByUsername(@PathVariable String username) {
		return ResponseEntity.ok(usersfacade.searchByUsername(username));
	}
	@GetMapping(path="test")
	public String test()
	{
		return "its running";
	}
	  
}
