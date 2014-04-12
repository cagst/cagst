package com.cagst.common.codevalue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Maps a row in the resultset into a {@link CGTCodeSet} object. Used to unmarshall a CodeSet from
 * the database.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTCodeSetRowMapper implements RowMapper<CGTCodeSet> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CGTCodeSet mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		CGTCodeSetBuilder codeset = new CGTCodeSetBuilder();

		codeset.setCodeSetUID(rs.getLong("cgt_codeset_id"));
		codeset.setDisplay(rs.getString("codeset_display"));
		codeset.setMeaning(rs.getString("codeset_meaning"));
		codeset.setActive(rs.getBoolean("active_ind"));
		codeset.setCodeSetUpdateCount(rs.getInt("updt_cnt"));

		return codeset.build();
	}

}
