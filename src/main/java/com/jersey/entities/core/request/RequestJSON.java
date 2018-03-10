package com.jersey.entities.core.request;

import com.jersey.entities.core.UserModel;

/**
 * This class represents the blueprint of json request
 */
public class RequestJSON {

	private UserModel user;
	/**
	 * @return the user
	 */
	public UserModel getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserModel user) {
		this.user = user;
	}
   
}
