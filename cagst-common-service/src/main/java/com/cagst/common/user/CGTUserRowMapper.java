package com.cagst.common.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.jdbc.core.RowMapper;

import com.cagst.common.person.CGTUser;
import com.cagst.common.person.CGTUserBuilder;

/**
 * Maps a row in the resultset into a {@link CGTUser} object. Used to unmarshall a {@link CGTUser}
 * object from the database.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public final class CGTUserRowMapper implements RowMapper<CGTUser> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CGTUser mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		CGTUserBuilder builder = new CGTUserBuilder();

		builder.setUserUID(rs.getLong("cgt_user_id"));
		builder.setPersonUID(rs.getLong("cgt_person_id"));
		builder.setLastName(rs.getString("name_last"));
		builder.setFirstName(rs.getString("name_first"));
		builder.setMiddleName(rs.getString("name_middle"));
		builder.setUsername(rs.getString("username"));
		builder.setPassword(rs.getString("password"));
		builder.setChangePassword(rs.getBoolean("change_password_ind"));
		builder.setSigninAttempts(rs.getInt("signin_attempts"));

		Date dob = rs.getDate("dob_dt_tm");
		if (dob != null) {
			builder.setDob(new DateTime(dob, DateTimeZone.UTC));
		}

		Date expireDate = rs.getDate("expire_dt_tm");
		if (expireDate != null) {
			builder.setExpireDate(new DateTime(expireDate, DateTimeZone.UTC));
		}

		Date lastSigninDate = rs.getDate("last_signin_dt_tm");
		if (lastSigninDate != null) {
			builder.setLastSigninDate(new DateTime(lastSigninDate, DateTimeZone.UTC));
		}

		Date accountLockedDate = rs.getDate("account_locked_dt_tm");
		if (accountLockedDate != null) {
			builder.setAccountedLockedDate(new DateTime(accountLockedDate, DateTimeZone.UTC));
		}

		builder.setActive(rs.getBoolean("active_ind"));
		builder.setUserUpdateCount(rs.getLong("updt_cnt"));
		builder.setUpdateDate(new DateTime(rs.getDate("updt_dt_tm")));

		return builder.build();
	}

}
