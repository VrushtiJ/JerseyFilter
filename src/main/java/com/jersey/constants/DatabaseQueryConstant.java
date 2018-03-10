package com.jersey.constants;

/**
 * The Class DatabaseQueryConstant.
 */
public class DatabaseQueryConstant {

	
	/** The Constant QUERY_TO_GET_USERID. */
	public static final String QUERY_TO_GET_USERID = "select USER_ID from USER where USER_ID=?";

	/** The Constant Query_For_EmailId. */
	public static final String Query_For_EmailId = "select USER_EMAIL from USER where USER_ID=?";

}
