package com.tweetapp.facade;

import com.tweetapp.payload.response.UsersResponseList;

public interface UsersFacade {

	public UsersResponseList getAllUsers();

	public UsersResponseList searchByUsername(String username);

}
