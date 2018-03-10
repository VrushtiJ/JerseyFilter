package com.jersey.entities.core;

import java.util.List;

/**
 * This class is the blueprint of the User table
 */
public class UserModel {

	// First Grouping of User data

	private long userId;

	private String userName;
	
	private long userOTP;

	private String password;
	
	private String idToken;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the idToken
	 */
	public String getIdToken() {
		return idToken;
	}

	/**
	 * @param idToken the idToken to set
	 */
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	/**
	 * @return the userOTP
	 */
	public long getUserOTP() {
		return userOTP;
	}

	/**
	 * @param userOTP the userOTP to set
	 */
	public void setUserOTP(long userOTP) {
		this.userOTP = userOTP;
	}

	private String userEmail;

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
