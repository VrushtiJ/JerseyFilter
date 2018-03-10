
package com.jersey.constants;

/**
 * This class defines the common constants like success , failure etc for json
 * Response.
 */
public class CommonConstants {

	/** The Constant ERROR_INPUT_JSON_NULL. */
	public static final String ERROR_INPUT_JSON_NULL = "2";

	/** The Constant ERROR_NOT_AN_EXISTING_USER. */
	public static final String ERROR_NOT_AN_EXISTING_USER = "3";

	/** The Constant ERROR_EXISTING_USER. */
	public static final String ERROR_EXISTING_USER = "4";

	/** The Constant ERROR_USER_AUTHENTICATION_FAILED. */
	public static final String ERROR_USER_AUTHENTICATION_FAILED = "5";

	/** The Constant AUTHENTIC_USER. */
	public static final String AUTHENTIC_USER = "6";

	/** The Constant ERROR_INPUT_JSON_NO_USER. */
	public static final String ERROR_INPUT_JSON_NO_USER = "7";

	/** The Constant ERROR_EXCEPTION_OCCURRED. */
	public static final String ERROR_EXCEPTION_OCCURRED = "8";

	/** The Constant ERROR_INVALID_USER_ID. */
	public static final String ERROR_INVALID_USER_ID = "9";
	
	/** The Constant BLANK_STRING. */
	public static final String BLANK_STRING = "";
	/** The failure. */
	public static final String FAILURE = "failure";

	/** The success. */
	public static final String SUCCESS = "success";

	/** The empty string. */
	public static final String EMPTY_STRING = "";

	/** The no error. */
	public static final String NO_ERROR = "0";

	/**  Errors specific to User login. */
	public static final String INVALID_USER_ID = "1";

	/**  (userId,emailId) combination fails. */
	public static final String INVALID_USER = "9";
	
	/** The Constant ERROR_INVALID_USER_EMAIL. */
	public static final String ERROR_INVALID_USER_EMAIL_DOMAIN="14";
	
	/** The Constant Domain. */
	public static final String Domain="@";
	
	/**Error code for invalid token	 */
	public static final String INVALID_SIGNED_TOKEN = "25";
	
	public static final String NO_IDTOKEN_FOUND_IN_DB = "26";
}
