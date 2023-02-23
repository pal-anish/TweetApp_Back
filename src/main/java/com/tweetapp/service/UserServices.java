package com.tweetapp.service;

import com.tweetapp.payload.response.UsersResponseList;

public interface UserServices {

	public UsersResponseList getAllUsers();

	public UsersResponseList searchByUsername(String username);

}
