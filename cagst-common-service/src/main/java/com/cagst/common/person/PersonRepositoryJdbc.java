package com.cagst.common.person;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import com.cagst.common.db.BaseRepositoryJdbc;
import com.cagst.common.db.StatementLoader;

/**
 * JDBC Template implementation of the {@link PersonRepository} interface.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class PersonRepositoryJdbc extends BaseRepositoryJdbc implements PersonRepository {
	private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryJdbc.class);

	private static final String INSERT_PERSON = "INSERT_PERSON";
	private static final String UPDATE_PERSON = "UPDATE_PERSON";

	/**
	 * Primary constructor used to create an instance of PersonRepositoryJdbc.
	 * 
	 * @param dataSource
	 *          The {@link DataSource} to use to retrieve/persist to/from.
	 */
	public PersonRepositoryJdbc(final DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void save(final CGTPersonBuilder person, final CGTUser user) throws IncorrectResultSizeDataAccessException,
			OptimisticLockingFailureException, DataAccessException {

		Assert.notNull(person);
		Assert.notNull(user);

		logger.info("Saving CGTPerson [{} {}].", person.getFirstName(), person.getLastName());

		if (person.getPersonUID() == 0L) {
			insertPerson(person, user);
		} else {
			updatePerson(person, user);
		}
	}

	private void insertPerson(final CGTPersonBuilder person, final CGTUser user) {
		logger.info("Inserting CGTPerson [{} {}].", person.getFirstName(), person.getLastName());

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());

		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			int insertCount = getJdbcTemplate().update(stmtLoader.load(INSERT_PERSON),
					PersonMapper.mapInsertStatement(person, user), keyHolder);
			if (insertCount != 1L) {
				throw new IncorrectResultSizeDataAccessException(1, insertCount);
			}

			person.setPersonUID(keyHolder.getKey().longValue());
		} catch (DataAccessException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	private void updatePerson(final CGTPersonBuilder person, final CGTUser user) {
		logger.info("Updating CGTPerson [{} {}].", person.getFirstName(), person.getLastName());

		StatementLoader stmtLoader = StatementLoader.getLoader(getClass(), getStatementDialect());

		try {
			int updateCount = getJdbcTemplate().update(stmtLoader.load(UPDATE_PERSON),
					PersonMapper.mapUpdateStatement(person, user));
			if (updateCount != 1L) {
				throw new OptimisticLockingFailureException("invalid update count of [" + updateCount
						+ "] possible update count mismatch");
			}
		} catch (DataAccessException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
}
