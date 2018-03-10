package com.jersey.dao;

public interface IUserDao {

	
	 /**
	  * Checks whether emailId exists or not in the database
	  * 
	  * @param emailId
	  * @return
	  */
	 public boolean isEmailIdExists(String emailId);
	 
	 /**
	  * Used to fetch userId based on userEmail
	  * 
	  * @param userName
	  * @return userId
	  */
	 public long getUserId(String userEmail);
	 
	 /**
	  * Get user email id of give user id
	  * @param userId
	  * @return
	  */
	 public String getUserEmail(long userId);
}
