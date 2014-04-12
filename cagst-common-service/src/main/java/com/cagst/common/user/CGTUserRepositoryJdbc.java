package com.cagst.common.user;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.util.Assert;

import com.cagst.common.db.BaseRepositoryJdbc;
import com.cagst.common.db.StatementLoader;
import com.cagst.common.person.CGTUser;
import com.cagst.common.person.CGTUserBuilder;

/**
 * JDBC Template implementation of the {@link CGTUserRepository} interface.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public final class CGTUserRepositoryJdbc extends BaseRepositoryJdbc implements CGTUserRepository {
	private static Logger logger = LoggerFactory.getLogger(CGTUserRepositoryJdbc.class);

	private static final String LOAD_USER_BY_USERNAME = "LOAD_USER_BY_USERNAME";
	private static final String INCREMENT_SIGNIN_ATTEMPTS = "INCREMENT_SIGNIN_ATTEMPTS";
	private static final String UPDATE_LAST_SIGNIN_DATETIME = "UPDATE_LAST_SIGNIN_DATETIME";
	private static final String CHANGE_USER_PASSWORD = "CHANGE_USER_PASSWORD";

	/**
	 * Primary constructor used to create an instance of CGTUserRepositoryJdbc.
	 * 
	 * @param dataSource
	 *          The {@link DataSource} used to retrieve / persist data objects.
	 */
	public CGTUserRepositoryJdbc(final DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public CGTUser getUserByUsername(final String username) throws IllegalArgumentException {
		Assert.hasLength(username, "username cannot be null or empty");

		logger.info("Retrieving CGTUser with username of [{}].", username);

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);

		try {
			return getJdbcTemplate().queryForObject(stmtLoader.load(LOAD_USER_BY_USERNAME), params, new CGTUserRowMapper());
		} catch (IncorrectResultSizeDataAccessException ex) {
			logger.warn("CGTUser with username [{}] was not found!", username);
			return null;
		}
	}

	@Override
	public CGTUser incrementSigninAttempts(final CGTUser user) throws IllegalArgumentException {
		Assert.notNull(user, "user cannot be null");

		logger.info("Incrementing signin attempts for CGTUser [{}].", user.getUsername());

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("cgt_user_id", user.getUserUID());

		int updateCount = getJdbcTemplate().update(stmtLoader.load(INCREMENT_SIGNIN_ATTEMPTS), params);
		if (updateCount != 1L) {

		}

		CGTUserBuilder builder = new CGTUserBuilder(user);
		builder.setSigninAttempts(builder.getSigninAttempts() + 1);

		return builder.build();
	}

	@Override
	public CGTUser updateLastSigninDatetime(final CGTUser user) throws IllegalArgumentException {
		Assert.notNull(user, "user cannot be null");

		logger.info("Updating last sigin datetime for CGTUser [{}].", user.getUsername());

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("cgt_user_id", user.getUserUID());

		int updateCount = getJdbcTemplate().update(stmtLoader.load(UPDATE_LAST_SIGNIN_DATETIME), params);
		if (updateCount != 1L) {

		}

		CGTUserBuilder builder = new CGTUserBuilder(user);
		builder.setLastSigninDate(new DateTime(DateTimeZone.UTC));

		return builder.build();
	}

	@Override
	public CGTUser changeUserPassword(final CGTUser user, final String password) throws IllegalArgumentException {
		Assert.notNull(user, "user cannot be null");
		Assert.hasLength(password, "password cannot be null or empty");

		logger.info("Changing password for CGTUser with username of [{}].", user.getUsername());

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cgt_user_id", user.getUserUID());
		params.put("password", password);

		try {
			int updateCount = getJdbcTemplate().update(stmtLoader.load(CHANGE_USER_PASSWORD), params);
			if (updateCount == 1) {
				return getUserByUsername(user.getUsername());
			} else {
				return null;
			}
		} catch (DataAccessException ex) {
			logger.error("Error changing password!", ex);
			return null;
		}
	}
}
