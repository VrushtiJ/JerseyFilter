package com.jersey.services;

import com.jersey.entities.core.response.ErrorModel;

/**
 * 

 *            the element type for request JSON
 * @param <T>
 *            the generic type for response JSON
 */
public interface IUserService<E, T> {

	/**
	 * Used to login in the application if user exists else it will add a new
	 * user
	 * 
	 * @param request
	 *            the request
	 * @return the T
	 */
	public T login(String userId, E request);
	/**
	 * It validates the login request.
	 *
	 * @param authToken
	 *            the auth token
	 * @param userId
	 *            the user id
	 * @param request
	 *            the request
	 * @return the error model
	 */
	public ErrorModel validateLoginRequest(String userId, E request);
	
}
