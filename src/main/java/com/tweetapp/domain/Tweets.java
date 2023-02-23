package com.tweetapp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Document("tweets")
public class Tweets {

	@Id
	private String id;

	@Indexed
	private String tweetMessage;

	@DBRef
	private Users username;

	private Date createdAt;
	private String createdBy;
	private Date modifiedAt;
	private String modifiedBy;
	private Character isActive;
	@DBRef
	private Set<Likes> likes = new HashSet<>();

	

	@DBRef
	private Set<Comments> reTweets = new HashSet<>();

	public Tweets() {
		super();
	}

	public Tweets(String id, String tweetMessage, Users username) {
		super();
		this.id = id;
		this.tweetMessage = tweetMessage;
		this.username = username;
	}

	public Tweets(String tweetMessage, Users username) {
		super();
		this.tweetMessage = tweetMessage;
		this.username = username;
	}

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
	 * @return the tweetMessage
	 */
	public String getTweetMessage() {
		return tweetMessage;
	}

	/**
	 * @param tweetMessage the tweetMessage to set
	 */
	public void setTweetMessage(String tweetMessage) {
		this.tweetMessage = tweetMessage;
	}

	/**
	 * @return the username
	 */
	public Users getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(Users username) {
		this.username = username;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedAt
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the likes
	 */
	public Set<Likes> getLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}

	/**
	 * @return the isActive
	 */
	public Character getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the reTweets
	 */
	public Set<Comments> getReTweets() {
		return reTweets;
	}

	/**
	 * @param reTweets the reTweets to set
	 */
	public void setReTweets(Set<Comments> reTweets) {
		this.reTweets = reTweets;
	}

}
