package com.cagst.common.codevalue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import com.cagst.common.person.CGTClient;

/**
 * Maps a row in the resultset into a {@link CGTCodeValue} object. Used to unmarshall a CodeValue
 * from the database.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTCodeValueRowMapper implements RowMapper<CGTCodeValue> {
	private final CGTCodeSet codeset;
	private final CGTClient client;

	/**
	 * Primary Constructor used to create an instance of CGTCodeValueRowMapper.
	 * 
	 * @param codeset
	 *          The {@link CGTCodeSet} to associate the CodeValues to.
	 * @param client
	 *          The {@link CGTClient} the CodeValues are associated to. Can be <code>null</code> if we
	 *          don't care about the Client and want all global CodeValues.
	 */
	public CGTCodeValueRowMapper(final CGTCodeSet codeset, final CGTClient client) {
		Assert.notNull(codeset);

		this.codeset = codeset;
		this.client = client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CGTCodeValue mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		// ensure this CodeValue can be returned for this Client
		long clientUID = rs.getLong("cgt_client_id");
		if (clientUID > 0L) {
			// this CodeValue is associated to a Client
			// if we don't have Client or this Client isn't the Client associated to the CodeValue, then
			// return NULL
			if (client == null || client.getClientUID() != clientUID) {
				return null;
			}
		}

		CGTCodeValueBuilder codevalueBuilder = new CGTCodeValueBuilder();
		codevalueBuilder.setCodeValueUID(rs.getLong("cgt_codevalue_id"));
		codevalueBuilder.setCodeSet(codeset);
		codevalueBuilder.setClient(client);
		codevalueBuilder.setDisplay(rs.getString("codevalue_display"));
		codevalueBuilder.setMeaning(rs.getString("codevalue_meaning"));
		codevalueBuilder.setActive(rs.getBoolean("active_ind"));
		codevalueBuilder.setCodeValueUpdateCount(rs.getInt("updt_cnt"));

		return codevalueBuilder.build();
	}

}
