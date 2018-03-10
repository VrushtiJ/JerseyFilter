package com.jersey.services.imp;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.jersey.constants.CommonConstants;
import com.jersey.constants.webservices.WebServicesHeaderConstants;
import com.jersey.constants.webservices.WebServicesUrlConstants;
import com.jersey.controller.IUserController;
import com.jersey.entities.core.request.RequestJSON;
import com.jersey.entities.core.response.DataModel;
import com.jersey.entities.core.response.ErrorModel;
import com.jersey.entities.core.response.ResponseJSON;
import com.jersey.security.JwtTokenFactory;
import com.jersey.services.IUserService;

/**
 * This class provides the implementation of {@link IUserService} interface.
 */

@Service
@Path(WebServicesUrlConstants.USER_WEB_SERVICE_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UserService implements IUserService<RequestJSON, ResponseJSON> {

	private IUserController iuserController;

	public void setIuserController(IUserController iuserController) {

		this.iuserController = iuserController;
	}



	/*
	 * 
	 * Steps : login + registration 1) if user exists in the database then yes
	 * access and return the required JSON response 2) if user not exits in the
	 * database then create the profile with the given data
	 * 
	 */
	@Override
	@POST
	@Path(WebServicesUrlConstants.USER_LOGIN_PATH)
	public ResponseJSON login(@HeaderParam(WebServicesHeaderConstants.USER_ID) String userId, RequestJSON request) {

		System.out.println("Inside login!!");
		ResponseJSON responseJson = new ResponseJSON();
		responseJson.setError(validateLoginRequest(userId, request));
		responseJson.setData(new DataModel());
		String userEmail = request.getUser().getUserEmail().toLowerCase();
		String password = request.getUser().getPassword();
		String signedToken=null;
		try {
			ADA.sendPost(userEmail, password);
			String idToken = ADA.idToken;

			JwtTokenFactory jwtTokenFactory = new JwtTokenFactory();
			if (null != idToken || !idToken.isEmpty()) {
				signedToken = jwtTokenFactory.signJwtToken(request.getUser(), idToken, ADA.expiresOn, ADA.notBefore);

				responseJson.setStatus(CommonConstants.SUCCESS);
				request.getUser().setIdToken(idToken);
				responseJson.setSignedJwtToken(signedToken);
			}
		} catch (Exception e) {
			responseJson.setStatus(CommonConstants.FAILURE);
			responseJson.getError().setErrorCode(CommonConstants.ERROR_USER_AUTHENTICATION_FAILED);
			responseJson.getError().setErrorMessage("Invalid userid or password "+e);
		}
		try {
			if (CommonConstants.AUTHENTIC_USER.equals(responseJson.getError().getErrorCode())
					|| CommonConstants.ERROR_NOT_AN_EXISTING_USER
					.equals(responseJson.getError().getErrorCode())) {
				try {
					if ((CommonConstants.NO_ERROR.equals(responseJson.getError().getErrorCode()))) {
						responseJson.setStatus(CommonConstants.SUCCESS);
					}
				} catch (DuplicateKeyException e) {
					responseJson.getError()
					.setErrorMessage("User name alredy exists : Choose different user name");
				} catch (Exception e) {
					responseJson.getError().setErrorCode(CommonConstants.ERROR_EXCEPTION_OCCURRED);
					responseJson.getError().setErrorMessage("Exception occurred :: " + e);
				}
			} else {
				responseJson.setStatus(CommonConstants.FAILURE);
				responseJson.getError().setErrorMessage("login failed as User is not authentic");
			}
		} catch (Exception e) {
			responseJson.getError().setErrorCode(CommonConstants.ERROR_EXCEPTION_OCCURRED);
			responseJson.getError().setErrorMessage("Exception occurred :: " + e);
		}
		//	responseJson.setSignedJwtToken(signedToken);
		return responseJson;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jersey.services.IUserService#validateRequest(java.lang.
	 * String, java.lang.String, java.lang.Object)
	 */
	@Override
	public ErrorModel validateLoginRequest(@HeaderParam(WebServicesHeaderConstants.USER_ID) String userId,
			RequestJSON request) {
		ErrorModel errorModel = new ErrorModel();
		try {
			if (request != null) {

				String userEmail = request.getUser().getUserEmail().toLowerCase();

				if (userEmail.contains(CommonConstants.Domain)) {
					request.getUser().setUserEmail(userEmail);
				} else {
					errorModel.setErrorMessage("Invalid Email Id");
					errorModel.setErrorCode(CommonConstants.ERROR_INVALID_USER_EMAIL_DOMAIN);
				}

				// validating user login request based on the emailId
				errorModel = iuserController.validateUserRequest((request.getUser().getUserEmail()));
				if (errorModel.getErrorCode().equals(CommonConstants.AUTHENTIC_USER)) {
					request.getUser().setUserId(iuserController.getUserId(request.getUser().getUserEmail()));
				}

			} else {
				errorModel.setErrorCode(CommonConstants.ERROR_INPUT_JSON_NULL);
				errorModel.setErrorMessage("Invalid Argument :: Input JSON is null");
			}
		} catch (NumberFormatException e) {

			errorModel.setErrorCode(CommonConstants.INVALID_USER_ID);
			errorModel.setErrorMessage("Invalid Argument :: User id is invalid in the header");
		}

		return errorModel;
	}
}
