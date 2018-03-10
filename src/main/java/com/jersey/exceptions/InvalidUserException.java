/**
 * 
 */
package com.jersey.exceptions;

/**
 * @author pramul kant
 *
 */
public class InvalidUserException extends Exception {
	private String message = null;

	public InvalidUserException(String message) {
		this.message=message;

	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
