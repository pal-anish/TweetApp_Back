package com.tweetapp.payload.response;

import java.util.ArrayList;
import java.util.List;

public class TweetResponseList {

	private List<TweetResponse> tweets = new ArrayList<>();

	/**
	 * @return the tweets
	 */
	public List<TweetResponse> getTweets() {
		return tweets;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<TweetResponse> tweets) {
		this.tweets = tweets;
	}

}
