package com.jersey.entities.core.response;

import com.jersey.constants.CommonConstants;

public class ResponseJSON {


	private String status=CommonConstants.FAILURE;

	private DataModel data;

	private ErrorModel error;

	private String signedJwtToken;


	/**
	 * @return the jwtTokens
	 */
	public String getSignedJwtToken() {
		return signedJwtToken;
	}

	/**
	 * @param jwtTokens the jwtTokens to set
	 */
	public void setSignedJwtToken(String jwtTokens) {
		this.signedJwtToken = jwtTokens;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public DataModel getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(DataModel data) {
		this.data = data;
	}

	/**
	 * @return the error
	 */
	public ErrorModel getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(ErrorModel error) {
		this.error = error;
	}

}
