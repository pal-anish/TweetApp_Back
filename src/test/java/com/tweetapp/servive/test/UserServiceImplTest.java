package com.tweetapp.servive.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.dao.UsersDao;
import com.tweetapp.domain.Users;
import com.tweetapp.payload.response.UsersResponseList;
import com.tweetapp.serviceImpl.UserServicesImpl;
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
	@InjectMocks
	UserServicesImpl userServicesImpl;
	@Mock
	UsersDao userDao;
	@Test
	public void getAllUsersTest()
	{
		Users user1=new Users("Sebanti","Roy","roysebanti1312@gmail.com","sebs123","1234","8583842225");
		Users user2=new Users("Soumeli","Gupta","soumeli@gmail.com","sou123","7890","8583842290");
		List<Users> users=new ArrayList<Users>();
		users.add(user1);
		users.add(user2);
		when(userDao.getAllUsers()).thenReturn(users);
		UsersResponseList usersResponseList=userServicesImpl.getAllUsers();
		
		
		
	}
	@Test
	public void searchByUsernameTest()
	{
   
		Users user1=new Users("Sebanti","Roy","roysebanti1312@gmail.com","sebs123","1234","8583842225");
		Users user2=new Users("Soumeli","Gupta","soumeli@gmail.com","sebs123","7890","8583842290");
		List<Users> users=new ArrayList<Users>();
		users.add(user1);
		users.add(user2);
		when(userDao.searchByUsername(user1.getUsername())).thenReturn(users);
		UsersResponseList usersResponseList=userServicesImpl.searchByUsername(user1.getUsername());
		assertEquals(2,usersResponseList.getUsersList().size());
		
	}
	
}
