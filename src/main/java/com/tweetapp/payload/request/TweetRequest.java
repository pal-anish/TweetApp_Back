package com.tweetapp.payload.request;

import javax.validation.constraints.Size;

public class TweetRequest {

	private String id;

	private String tweetString;

	private String username;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the tweetString
	 */
	public String getTweetString() {
		return tweetString;
	}

	/**
	 * @param tweetString the tweetString to set
	 */
	public void setTweetString(String tweetString) {
		this.tweetString = tweetString;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public TweetRequest(String id, String tweetString, String username) {
		super();
		this.id = id;
		this.tweetString = tweetString;
		this.username = username;
	}
	

}
