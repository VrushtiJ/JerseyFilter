package com.jersey.entities.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jersey.entities.core.UserModel;

/**
 * This class contains the entities which are part of the data field in json
 * response.

 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataModel {

	/** The user. */
	private UserModel user;

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public UserModel getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserModel user) {
		this.user = user;
	}


}
