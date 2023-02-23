package com.tweetapp.payload.response;

import java.util.ArrayList;
import java.util.List;

public class UsersResponseList {

	List<UsersResponse> usersList = new ArrayList<>();

	/**
	 * @return the usersList
	 */
	public List<UsersResponse> getUsersList() {
		return usersList;
	}

	/**
	 * @param usersList the usersList to set
	 */
	public void setUsersList(List<UsersResponse> usersList) {
		this.usersList = usersList;
	}

}
