package com.tweetapp.servive.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.dao.TweetsDao;
import com.tweetapp.dao.UsersDao;
import com.tweetapp.domain.Likes;
import com.tweetapp.domain.Tweets;
import com.tweetapp.domain.Users;
import com.tweetapp.payload.request.TweetRequest;
import com.tweetapp.payload.response.TweetResponseList;
import com.tweetapp.repository.LikeRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.serviceImpl.TweetServicesImpl;
import com.tweetapp.util.TweetAppConstants;
@ExtendWith(SpringExtension.class)
public class TweetServiceImplTest {
	@InjectMocks
	TweetServicesImpl tweetServicesImpl;
	@Mock
	UsersDao usersDao;
	@Mock
	TweetsDao tweetsDao;
	@Mock
	LikeRepository likeRepository;
	@Mock
	UserRepository userRepository;
	@Test
	public void getAllTweetsTest()
	{
		Users user1=new Users("Sebanti","Roy","roysebanti1312@gmail.com","sebs123","1234","8583842225");
		Users user2=new Users("Soumeli","Gupta","soumeli@gmail.com","sou123","7890","8583842290");
		Tweets tweet1=new Tweets("t1", "This tweet 1",user1);
		tweet1.setCreatedAt(new Date());
		tweet1.setCreatedBy("sebanti");
		Tweets tweet2=new Tweets("t2","this is tweet 2",user2);
		tweet2.setCreatedAt(new Date());
		tweet2.setCreatedBy("soumeli");
		List<Tweets> tweets=new ArrayList<Tweets>();
		tweets.add(tweet1);
		tweets.add(tweet2);
		when(tweetsDao.getAllTweets()).thenReturn(tweets);
		TweetResponseList tweetResponseList=tweetServicesImpl.getAllTweets();
		assertEquals(2,tweetResponseList.getTweets().size());
	
	}
	@Test
	public void deleteTweetTest()
	{
		Users user1=new Users("Sebanti","Roy","roysebanti1312@gmail.com","sebs123","1234","8583842225");
		Tweets tweet1=new Tweets("t1", "This tweet 1",user1);
		when(tweetsDao.findTweetById(tweet1.getId())).thenReturn(tweet1);
		assertEquals(true,tweetServicesImpl.deleteTweet(user1.getUsername(), tweet1.getId()));
		String username="soumeli123";
		assertEquals(false,tweetServicesImpl.deleteTweet(username, tweet1.getId()));
		
	}
    @Test
	public void likeTweetTest()
	{
		Users user1=new Users("Sebanti","Roy","roysebanti1312@gmail.com","sebs123","1234","8583842225");
		Tweets tweet1=new Tweets("t1", "This tweet 1",user1);
		tweet1.setLikes(new HashSet<Likes>());
		when(tweetsDao.findTweetById(tweet1.getId())).thenReturn(tweet1);
		when(usersDao.getUserByUsername(user1.getUsername())).thenReturn(user1);
		Likes like=new Likes();
		like.setId("1");
		System.out.println(like.getId());
		when(likeRepository.save(like)).thenReturn(like);
		when(tweetsDao.save(tweet1)).thenReturn(tweet1);
		System.out.println(like.getIsActive());
		assertEquals(false,tweetServicesImpl.likeTweet(user1.getUsername(), tweet1.getId()));
		
		
	}
	@Test
	public void createTweetTest()
	{
		TweetServicesImpl tweet=mock(TweetServicesImpl.class);
		TweetRequest tweetRequest=new TweetRequest("1","Hiiii","sebanti");
		doNothing().when(tweet).createTweet(tweetRequest);
		tweet.createTweet(tweetRequest);
		verify(tweet,times(1)).createTweet(tweetRequest);
		
	}
	@Test
	public void updateTweetTest()
	{
		TweetServicesImpl tweetUpdate=mock(TweetServicesImpl.class);
		TweetRequest tweetRequest=new TweetRequest("1","Hiiii all","sebanti");
		doNothing().when(tweetUpdate).updateTweet(tweetRequest);
		tweetUpdate.updateTweet(tweetRequest);
		verify(tweetUpdate,times(1)).updateTweet(tweetRequest);
		
	}
	@Test
	public void replyToTweetTest()
	{
		TweetServicesImpl tweet=mock(TweetServicesImpl.class);
		TweetRequest tweetRequest=new TweetRequest("1","Hiiii","sebanti");
		String tweetId="1";
		doNothing().when(tweet).replyToTweet(tweetRequest, tweetId);
		tweet.replyToTweet(tweetRequest, tweetId);
		verify(tweet,times(1)).replyToTweet(tweetRequest, tweetId);
	}
@Test
public void getAllTweetsByUsername() {
	Users user1=new Users("Sebanti","Roy","roysebanti1312@gmail.com","sebs123","1234","8583842225");
	String username="sebs123";
	Tweets tweet1=new Tweets("t1", "This tweet 1",user1);
	Set<Tweets> tweets=new HashSet<Tweets>();
	tweets.add(tweet1);
	user1.setTweets(tweets);
	when(usersDao.getUserByUsername(username)).thenReturn(user1);
	TweetResponseList tweetResponseList=tweetServicesImpl.getAllTweetsByUsername(username);
	assertEquals(1,tweetResponseList.getTweets().size());

	}
}

