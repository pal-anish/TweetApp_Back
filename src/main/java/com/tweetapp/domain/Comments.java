package com.tweetapp.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "comments")
public class Comments {
	@Id
	private String id;
	@DBRef
	private Tweets tweet;
	@DBRef
	private Users username;
	@Indexed
	private String tweetMessage;
	private Date createdAt;
	private Date modifiedAt;
	private String createdBy;
	private String modifiedBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Tweets getTweet() {
		return tweet;
	}
	public void setTweet(Tweets tweet) {
		this.tweet = tweet;
	}
	public Users getUsername() {
		return username;
	}
	public void setUsername(Users username) {
		this.username = username;
	}
	public String getTweetMessage() {
		return tweetMessage;
	}
	public void setTweetMessage(String tweetMessage) {
		this.tweetMessage = tweetMessage;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Comments(Users username, String tweetMessage) {
		super();
		this.username = username;
		this.tweetMessage = tweetMessage;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
