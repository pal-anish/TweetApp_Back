package com.tweetapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UsersDao;
import com.tweetapp.domain.Users;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.payload.response.UsersResponse;
import com.tweetapp.payload.response.UsersResponseList;
import com.tweetapp.service.UserServices;

@Service
public class UserServicesImpl implements UserServices {

	private static final Logger LOG = LoggerFactory.getLogger(UserServicesImpl.class);

	@Autowired
	UsersDao userDao;

	@Override
	public UsersResponseList getAllUsers() {
		try {
			List<Users> users = userDao.getAllUsers();
			System.out.println(users.size());
			for(Users u:users) System.out.println(u.getUsername());
			UsersResponseList usersResponseList = new UsersResponseList();
			List<UsersResponse> usersResponses = new ArrayList<>();
			users.forEach(user -> {
				UsersResponse usersResponse = new UsersResponse();
				usersResponse.setId(user.getId());
				usersResponse.setName(user.getFirstName() + " " + user.getLastName());
				usersResponse.setUsername(user.getUsername());
				usersResponses.add(usersResponse);
				
			});
			usersResponseList.setUsersList(usersResponses);
			return usersResponseList;
			
		}
		catch(Exception e)
		{
			throw new UserNotFoundException("user not found");
		}
		
	}

	@Override
	public UsersResponseList searchByUsername(String username) {
		try {
			List<Users> users = userDao.searchByUsername(username);
			UsersResponseList usersResponseList = new UsersResponseList();
			List<UsersResponse> usersResponses = new ArrayList<>();
			users.forEach(user -> {
				UsersResponse usersResponse = new UsersResponse();
				usersResponse.setId(user.getId());
				usersResponse.setName(user.getFirstName() + " " + user.getLastName());
				usersResponse.setUsername(user.getUsername());
				usersResponses.add(usersResponse);
			});
			usersResponseList.setUsersList(usersResponses);
			return usersResponseList;
		}
		catch(Exception e)
		{
			throw new UserNotFoundException("user not found");
		}
	}

}
