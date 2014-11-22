package com.cagst.common.person;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * Definition of a repository that retrieves and persists {@link CGTPerson} objects.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public interface PersonRepository {
  /**
   * Persist the specified {@link CGTPersonBuilder} to persistent storage.
   *
   * @param person
   *     The {@link CGTPersonBuilder} to persist.
   * @param user
   *     The {@link CGTUser} that performed the changes.
   *
   * @throws IncorrectResultSizeDataAccessException
   *     if the updt_cnt doesn't match (meaning someone has updated it since it was last
   *     read).
   * @throws OptimisticLockingFailureException
   *     if more than 1 row was inserted/updated.
   * @throws DataAccessException
   *     for all other failures.
   */
  public void save(final CGTPersonBuilder person, final CGTUser user) throws IncorrectResultSizeDataAccessException,
      OptimisticLockingFailureException, DataAccessException;
}
