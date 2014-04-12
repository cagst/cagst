package com.cagst.common.codevalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.cagst.common.db.BaseRepositoryJdbc;
import com.cagst.common.db.StatementLoader;
import com.cagst.common.person.CGTClient;

/**
 * JDBC Template implementation of the {@link CGTCodeValueRepository} interface.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTCodeValueRepositoryJdbc extends BaseRepositoryJdbc implements CGTCodeValueRepository {
	private static final Logger logger = LoggerFactory.getLogger(CGTCodeValueRepositoryJdbc.class);

	private static final String LOAD_CODESET_BY_UID = "LOAD_CODESET_BY_UID";
	private static final String LOAD_CODEVALUES_FOR_CODESET = "LOAD_CODEVALUES_FOR_CODESET";

	private String stmtDialect = StatementLoader.MYSQL_DIALECT;

	/**
	 * Primary Constructor used to create an instance of the CodeValueRepositoryJdbc.
	 * 
	 * @param dataSource
	 *          The {@link DataSource} used to retrieve / persist data objects.
	 */
	public CGTCodeValueRepositoryJdbc(final DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * @return The current dialect being used to retrieve SQL resource statements.
	 */
	@Override
	public String getStatementDialect() {
		return stmtDialect;
	}

	/**
	 * Sets the dialect to use to retrieve SQL resource statements.
	 * 
	 * @param stmtDialect
	 *          The new dialect to use to retrieve SQL resource statements, "MySql" is the default.
	 */
	@Override
	public void setStatementDialect(final String stmtDialect) {
		this.stmtDialect = stmtDialect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cagst.common.service.codevalue.CodeValueRepository#getCodeSetByUID(long)
	 */
	@Override
	public CGTCodeSet getCodeSetByUID(final long uid) {
		logger.info("Retrieving CodeSetByUID...");

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("cgt_codeset_id", uid);

		List<CGTCodeSet> codesets = getJdbcTemplate().query(stmtLoader.load(LOAD_CODESET_BY_UID), params,
				new CGTCodeSetRowMapper());
		if (codesets != null && codesets.size() == 1) {
			return codesets.get(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cagst.common.service.codevalue.CodeValueRepository#getCodeValuesForCodeSet(com.cagst.common
	 * .domain.codevalue .CGTCodeSet, com.cagst.common.domain.person.CGTClient)
	 */
	@Override
	public List<CGTCodeValue> getCodeValuesForCodeSet(final CGTCodeSet codeset, final CGTClient client) {
		logger.info("Retrieving CodeValuesForCodeSet...");

		Assert.notNull(codeset);

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("cgt_codeset_id", codeset.getCodeSetUID());

		List<CGTCodeValue> codeValues = getJdbcTemplate().query(stmtLoader.load(LOAD_CODEVALUES_FOR_CODESET), params,
				new CGTCodeValueRowMapper(codeset, client));

		List<CGTCodeValue> results = new ArrayList<CGTCodeValue>();
		for (CGTCodeValue codeValue : codeValues) {
			if (codeValue != null) {
				results.add(codeValue);
			}
		}

		return results;
	}
}
