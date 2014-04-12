package com.cagst.common.person;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * Used to marshal/unmarshal a {@link CGTPerson} to/from the database.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class PersonMapper {
	/**
	 * Will marshal a {@link CGTPersonBuilder} to a {@link MapSqlParameterSource} for inserting into
	 * the database.
	 * 
	 * @param person
	 *          The {@link CGTPersonBuilder} to map into an insert statement.
	 * @param user
	 *          The {@link CGTUser} that performed the changes.
	 * 
	 * @return A {@link MapSqlParameterSource} that can be used in a <code>jdbcTemplate.update</code>
	 *         statement.
	 */
	public static final MapSqlParameterSource mapInsertStatement(final CGTPersonBuilder person, final CGTUser user) {
		MapSqlParameterSource params = new MapSqlParameterSource();

		return params;
	}

	public static final MapSqlParameterSource mapUpdateStatement(final CGTPersonBuilder person, final CGTUser user) {
		MapSqlParameterSource params = new MapSqlParameterSource();

		return params;
	}
}
