/**
 * 
 */
package com.jersey.authFilter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.stereotype.Repository;

import com.jersey.constants.CommonConstants;
import com.jersey.constants.webservices.WebServicesHeaderConstants;
import com.jersey.constants.webservices.WebServicesUrlConstants;
import com.jersey.controller.IUserController;
import com.jersey.dao.IUserDao;
import com.jersey.entities.core.UserModel;
import com.jersey.entities.core.response.ErrorModel;
import com.jersey.security.JwtTokenFactory;

/**
 * @author vrushti
 *
 */
@Repository
@Priority(value=1)
@Provider
public class TokenAuthenticationFilter implements ContainerRequestFilter {

	/**
	 * Dao reference for user profile
	 */
	private IUserDao iUserDao;

	/**
	 * 
	 * @param iuserdao
	 */
	public void setIUserDao(IUserDao iuserdao) {
		this.iUserDao = iuserdao;

	}
	/* (non-Javadoc)
	 * @see javax.ws.rs.client.ClientRequestFilter#filter(javax.ws.rs.client.ClientRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		JwtTokenFactory jwtTokenFactory = new JwtTokenFactory();
		ContainerRequest containerRequest =(ContainerRequest) requestContext;
		System.out.println("insider Filter!!"); 
		String urlPath = containerRequest.getPath(true);
		switch(urlPath)
		{
		case WebServicesUrlConstants.USER_URL_PATH:
			return;

		default:
			break;
		}

		long userId = Long.valueOf(containerRequest.getHeaderString(WebServicesHeaderConstants.USER_ID));
		String userEmail = "";

		try{
			userEmail=iUserDao.getUserEmail(userId);
		}
		catch(Exception e)
		{
			ErrorModel errorModel = new ErrorModel();
			errorModel.setErrorCode(CommonConstants.NO_IDTOKEN_FOUND_IN_DB);
			errorModel.setErrorMessage("User doesn't exist in the system");

			requestContext.abortWith(
					Response.status(Response.Status.BAD_REQUEST)
					.entity(errorModel)
					.build());
			e.printStackTrace();
		}

		try{
			if(requestContext.getHeaderString(WebServicesHeaderConstants.TOKEN_ID) != null 
					&& jwtTokenFactory.isTokenValid(requestContext.getHeaderString(WebServicesHeaderConstants.TOKEN_ID),userEmail))
			{
				return;
			}
			else
			{
				System.out.println("Not correct token");
				ErrorModel errorModel = new ErrorModel();
				errorModel.setErrorCode(CommonConstants.INVALID_SIGNED_TOKEN);
				errorModel.setErrorMessage("Not a valid id token");

				requestContext.abortWith(
						Response.status(Response.Status.BAD_REQUEST)
						.entity(errorModel)
						.build());
			}
		}
		catch (Exception e)
		{
			ErrorModel errorModel = new ErrorModel();
			errorModel.setErrorCode(CommonConstants.INVALID_SIGNED_TOKEN);
			errorModel.setErrorMessage("Not a valid id token");

			requestContext.abortWith(
					Response.status(Response.Status.BAD_REQUEST)
					.entity(errorModel)
					.build());
			e.printStackTrace();
		}
	}
}
