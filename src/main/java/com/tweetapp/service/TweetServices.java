package com.tweetapp.service;

import com.tweetapp.payload.request.TweetRequest;
import com.tweetapp.payload.response.TweetResponseList;

public interface TweetServices {

	public void createTweet(TweetRequest tweetRequest);

	public void updateTweet(TweetRequest tweetRequest);

	public TweetResponseList getAllTweets();

	public Boolean deleteTweet(String username, String id);

	public Boolean likeTweet(String username, String id);

	public TweetResponseList getAllTweetsByUsername(String username);

	public void replyToTweet(TweetRequest tweetRequest, String tweetId);
}
