package com.tweetapp.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import nonapi.io.github.classgraph.json.Id;

@Document(collection = "roles")
public class Role {

	@Id
	private String id;
	private ERole name;

	public Role() {
		super();
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
	 * @return the name
	 */
	public ERole getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(ERole name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public ERole getRole() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setRole(ERole role) {
		this.name = role;
	}

}
