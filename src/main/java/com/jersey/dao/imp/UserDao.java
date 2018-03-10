
package com.jersey.dao.imp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jersey.dao.IUserDao;

/**
 * This class is realization of {@link IUserDao} and is responsible for
 * interacting with the User table
 */
@Repository
@Transactional
public class UserDao implements IUserDao {

	/* means of getting connection with the physical database */
	private DataSource dataSource;

	/*
	 * responsible for doing jdbc specific task before and after execution of
	 * query
	 */
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see com.jersey.dao.IUserDao#isEmailIdExists(java.lang.String)
	 */
	@Override
	public boolean isEmailIdExists(String emailId) {
		String sql = "Select USER_EMAIL from USER where USER_EMAIL=?";

		try {
			String emailID = jdbcTemplate.queryForObject(sql, new Object[] { emailId }, String.class);
			if (emailId.equals(emailID))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.jersey.dao.IUserDao#getUserId(java.lang.String)
	 */
	@Override
	public long getUserId(String userEmail) {
		String sql = "select USER_ID from USER where USER_EMAIL=?";
		long userID = jdbcTemplate.queryForObject(sql, new Object[] { userEmail }, Long.class);

		return userID;
	}

	/* (non-Javadoc)
	 * @see com.jersey.dao.IUserDao#getUserEmail(long)
	 */
	@Override
	public String getUserEmail(long userId) {
		String sql = "select USER_EMAIL from USER where USER_ID=?";
		String userEmail = jdbcTemplate.queryForObject(sql, new Object[] { userId }, String.class);

		return userEmail;
	}


}

