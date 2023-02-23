package com.tweetapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetsDao;
import com.tweetapp.dao.UsersDao;
import com.tweetapp.domain.Comments;
import com.tweetapp.domain.Likes;
import com.tweetapp.domain.Tweets;
import com.tweetapp.domain.Users;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.payload.request.TweetRequest;
import com.tweetapp.payload.response.TweetResponse;
import com.tweetapp.payload.response.CommentResponse;
import com.tweetapp.payload.response.TweetResponseList;
import com.tweetapp.repository.CommentRepository;
import com.tweetapp.repository.LikeRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.TweetServices;
import com.tweetapp.util.TweetAppConstants;

@Service
public class TweetServicesImpl implements TweetServices {

	private static final Logger LOG = LoggerFactory.getLogger(TweetServices.class);

	@Autowired
	UsersDao usersDao;

	@Autowired
	TweetsDao tweetsDao;

	@Autowired
	LikeRepository likeRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepo;

	@Override
	public void createTweet (TweetRequest tweetRequest) throws TweetNotFoundException{
		LOG.info("inside createTweet()");
		Users user = usersDao.getUserByUsername(tweetRequest.getUsername());
		Tweets tweet = new Tweets(tweetRequest.getTweetString(), user);
		tweet.setCreatedBy(tweetRequest.getUsername());
		tweet.setCreatedAt(new Date());
		tweet = tweetsDao.save(tweet);
		if (tweet.getId() != null) {
			Set<Tweets> tweets = user.getTweets();
			tweets.add(tweet);
			user.setTweets(tweets);
			userRepository.save(user);
		}
	}

	@Override
	public void updateTweet(TweetRequest tweetRequest)throws TweetNotFoundException {
		LOG.info("inside updateTweet()");
		Tweets tweet = tweetsDao.findTweetById(tweetRequest.getId());
		Users users = usersDao.getUserByUsername(tweetRequest.getUsername());
		LOG.info("User is havin Admin Role: " + usersDao.isUserAdmin(tweetRequest.getUsername()));
		if (tweet.getUsername().getId().equals(users.getId()) || usersDao.isUserAdmin(tweetRequest.getUsername())) {
			LOG.info("tweetString"+tweetRequest.getTweetString());
			tweet.setTweetMessage(tweetRequest.getTweetString());
			tweet.setModifiedAt(new Date());
			tweet.setModifiedBy(tweetRequest.getUsername());
			tweetsDao.save(tweet);
		}
	}
	
	@Override
	public TweetResponseList getAllTweets() throws TweetNotFoundException {
	
		List<Tweets> allTweets = tweetsDao.getAllTweets();
		Collections.reverse(allTweets);
		TweetResponseList tweetList = new TweetResponseList();
		List<TweetResponse> tweets = new ArrayList<>();
		allTweets.forEach(tweet -> {
			TweetResponse tr = new TweetResponse();
			tr.setId(tweet.getId());
			tr.setTweetMessage(tweet.getTweetMessage());
			tr.setUsername(tweet.getUsername().getUsername());
			tr.setCreatedBy(tweet.getCreatedBy());
			tr.setCtearedAt(tweet.getCreatedAt());
			tr.setFirstName(tweet.getUsername().getFirstName());
			tr.setLastName(tweet.getUsername().getLastName());
			Set<Likes> like=tweet.getLikes();
			//System.out.println(like.size());
			Set<Comments> reTweet=tweet.getReTweets();
			Set<CommentResponse> comments=new HashSet<CommentResponse>();
			
			if(reTweet!=null)
			{
			for(Comments t:reTweet)
			{
					CommentResponse t1=new CommentResponse();
					t1.setId(t.getId());
					t1.setTweetMessage(t.getTweetMessage());
					t1.setUsername(t.getUsername().getUsername());
					t1.setCreatedBy(t.getCreatedBy());
					t1.setCtearedAt(t.getCreatedAt());
					t1.setFirstName(t.getUsername().getFirstName());
					t1.setLastName(t.getUsername().getLastName());
				   comments.add(t1);
			}
		}
		  tr.setComment(comments);
			
			List<String> list=new ArrayList<String>();
			for(Likes l:like)
				{
				if(l.getIsActive()=='Y')
				{
					list.add(l.getCreatedBy());
				}
				}
			System.out.println(list.size());
			tr.setLike(list);
			//LOG.info(tweet.getLikes().toString());
			//LOG.info(tweet.toString());
			if (tweet.getModifiedBy() != null) {
				tr.setModifiedBy(tweet.getModifiedBy());
			}
			if (tweet.getModifiedAt() != null) {
				tr.setModifiedAt(tweet.getModifiedAt());
			}
			tweets.add(tr);
			
		});
		tweetList.setTweets(tweets);
		return tweetList;
	}

	@Override
	public Boolean deleteTweet (String username, String id) throws TweetNotFoundException {
		Tweets tweet = tweetsDao.findTweetById(id);
		if (tweet.getUsername().getUsername().equals(username) || usersDao.isUserAdmin(username)) {
			tweetsDao.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean likeTweet(String username, String id)throws TweetNotFoundException {
		Tweets tweet = tweetsDao.findTweetById(id);
		Likes like = null;
		if (!tweet.getLikes().isEmpty()) {
			Set<Likes> likes = tweet.getLikes();
			List<Likes> likedUser = likes.stream().filter(l -> l.getUsername().getUsername().equals(username))
					.collect(Collectors.toList());
			if (!likedUser.isEmpty()) {
				like = likedUser.get(0);
				if (like.getIsActive().equals(TweetAppConstants.CHARACTER_Y)) {
					like.setIsActive(TweetAppConstants.CHARACTER_N);
					
				} else if (like.getIsActive().equals(TweetAppConstants.CHARACTER_N)) {
					like.setIsActive(TweetAppConstants.CHARACTER_Y);
				}
			} else {
				like = new Likes();
				like.setTweet(tweet);
				like.setUsername(usersDao.getUserByUsername(username));
				like.setCreatedAt(new Date());
				like.setCreatedBy(username);
				like.setIsActive(TweetAppConstants.CHARACTER_Y);
			}
		} else {
			like = new Likes();
			like.setTweet(tweet);
			like.setUsername(usersDao.getUserByUsername(username));
			like.setCreatedAt(new Date());
			like.setCreatedBy(username);
			like.setIsActive(TweetAppConstants.CHARACTER_Y);
		}
	
		like = likeRepository.save(like);
		Set<Likes> likes = tweet.getLikes();
		likes.add(like);
		tweet.setLikes(likes);
		tweetsDao.save(tweet);
		System.out.println(like);
		if (like!= null) {
		 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public TweetResponseList getAllTweetsByUsername(String username) throws TweetNotFoundException{
		Users user = usersDao.getUserByUsername(username);
		TweetResponseList tweetList = new TweetResponseList();
		List<TweetResponse> tweets = new ArrayList<>();
		Set<Tweets> userTweets = user.getTweets();
		List<Tweets> userTweet=new ArrayList<>(userTweets);
		Collections.reverse(userTweet);
		userTweet.forEach(tweet -> {
			TweetResponse tr = new TweetResponse();
			tr.setId(tweet.getId());
			tr.setTweetMessage(tweet.getTweetMessage());
			tr.setUsername(tweet.getUsername().getUsername());
			tr.setCreatedBy(tweet.getCreatedBy());
			tr.setCtearedAt(tweet.getCreatedAt());
			tr.setFirstName(tweet.getUsername().getFirstName());
			tr.setLastName(tweet.getUsername().getLastName());
			Set<Comments> reTweet=tweet.getReTweets();
			Set<CommentResponse> comments=new HashSet<CommentResponse>();
			
			if(reTweet!=null)
			{
			for(Comments t:reTweet)
			{
					CommentResponse t1=new CommentResponse();
					t1.setId(t.getId());
					t1.setTweetMessage(t.getTweetMessage());
					t1.setUsername(t.getUsername().getUsername());
					t1.setCreatedBy(t.getCreatedBy());
					t1.setCtearedAt(t.getCreatedAt());
					t1.setFirstName(t.getUsername().getFirstName());
					t1.setLastName(t.getUsername().getLastName());
				   comments.add(t1);
			}
		}
		  tr.setComment(comments);
			Set<Likes> like=tweet.getLikes();
			List<String> list=new ArrayList<String>();
			for(Likes l:like)
				{
				if(l.getIsActive()=='Y')
				{
					list.add(l.getCreatedBy());
				}
				}
			System.out.println(list.size());
			tr.setLike(list);
			//tr.setLikes(tweet.getLikes());
			if (tweet.getModifiedBy() != null) {
				tr.setModifiedBy(tweet.getModifiedBy());
			}
			if (tweet.getModifiedAt() != null) {
				tr.setModifiedAt(tweet.getModifiedAt());
			}
			//LOG.info(tr.getLikes().toString());
			tweets.add(tr);
		});
	
		tweetList.setTweets(tweets);
		return tweetList;
	}

	@Override
	public void replyToTweet (TweetRequest tweetRequest, String tweetId) throws TweetNotFoundException{
		LOG.info("inside replyToTweet()");
		Users user = usersDao.getUserByUsername(tweetRequest.getUsername());
		Tweets tweet = tweetsDao.findTweetById(tweetId);
		Comments reTweet=new Comments(user, tweetRequest.getTweetString());
		//Tweets reTweet = new Tweets(tweetRequest.getTweetString(), user);
		reTweet.setTweet(tweet);
		reTweet.setCreatedBy(tweetRequest.getUsername());
		reTweet.setCreatedAt(new Date());
	
		reTweet = commentRepo.save(reTweet);
		if (reTweet.getCreatedBy() != null) {
			Set<Tweets> tweets = user.getTweets();
			Set<Comments> tweetSet = tweet.getReTweets();
			//tweets.add(reTweet);
			tweetSet.add(reTweet);
			user.setTweets(tweets);
			tweet.setReTweets(tweetSet);
			userRepository.save(user);
			tweetsDao.save(tweet);
		}
	}
}
