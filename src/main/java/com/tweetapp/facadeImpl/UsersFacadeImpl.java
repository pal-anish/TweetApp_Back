package com.tweetapp.facadeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.facade.UsersFacade;
import com.tweetapp.payload.response.UsersResponseList;
import com.tweetapp.service.UserServices;

@Component
public class UsersFacadeImpl implements UsersFacade {

	@Autowired
	UserServices userServices;

	@Override
	public UsersResponseList getAllUsers() {
		return userServices.getAllUsers();
	}

	@Override
	public UsersResponseList searchByUsername(String username) {
		return userServices.searchByUsername(username);
	}

}
