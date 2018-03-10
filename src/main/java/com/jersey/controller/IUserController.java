package com.jersey.controller;

import com.jersey.entities.core.response.ErrorModel;

/**
 * This interface act as an intermediate layer between Service layer and
 * Database layer. The implementation class of this interface will contain a
 * reference of IUserDao interface and IUserEventDao so that it can communicate
 * with User profile table and UserEvent table
 * 
 */
public interface IUserController {

	/**
	 * 
	 * @param userName
	 * @return
	 */
	public long getUserId(String userEmail);


	/**
	 * Validates the user login/registration request based on the emailId
	 * 
	 * @param email 
	 * @return errorModel
	 */
	public ErrorModel validateUserRequest(String email);


}
