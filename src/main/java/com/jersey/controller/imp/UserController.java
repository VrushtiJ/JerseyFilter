package com.jersey.controller.imp;

import org.springframework.stereotype.Controller;

import com.jersey.constants.CommonConstants;
import com.jersey.controller.IUserController;
import com.jersey.dao.IUserDao;
import com.jersey.entities.core.response.ErrorModel;

@Controller
public class UserController implements IUserController {

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
	 * @see com.jersey.controller.IUserController#validateUserRequest(java.lang.String)
	 */
	@Override
	public ErrorModel validateUserRequest(String email) {
		ErrorModel errorModel = new ErrorModel();

		boolean status = iUserDao.isEmailIdExists(email);
		if (status) {
			errorModel.setErrorCode(CommonConstants.AUTHENTIC_USER);
			errorModel.setErrorMessage("User is Authetic");
		} else {
			errorModel.setErrorCode(CommonConstants.ERROR_NOT_AN_EXISTING_USER);
			errorModel.setErrorMessage("User referes to new user, valid only for registration");
		}
		return errorModel;
	}
	/* (non-Javadoc)
	 * @see com.jersey.controller.IUserController#getUserId(java.lang.String)
	 */
	@Override
	public long getUserId(String userEmail) {
		return iUserDao.getUserId(userEmail);
	}

}
