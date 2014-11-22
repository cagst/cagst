package com.cagst.common.user;

import com.cagst.common.person.CGTUser;
import org.springframework.stereotype.Repository;

/**
 * Definition of a repository that retrieves and persists {@link CGTUser} objects.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
@Repository
public interface CGTUserRepository {
  /**
   * Retrieves a {@link CGTUser} based upon the specified username.
   *
   * @param username
   *     The {@link String} username that identifies the {@link CGTUser} to retrieve.
   *
   * @return The {@link CGTUser} associated to the specified username. <code>null</code> if no
   * {@link CGTUser} is associated to this username.
   *
   * @throws IllegalArgumentException
   *     if username is <code>null</code> or is empty.
   */
  public CGTUser getUserByUsername(final String username) throws IllegalArgumentException;

  /**
   * Increments the signin attempts for the specified {@link CGTUser}.
   *
   * @param user
   *     The {@link CGTUser} to update the signin attempts for.
   *
   * @return The {@link CGTUser} that has been updated accordingly.
   *
   * @throws IllegalArgumentException
   *     if <code>user</code> is null
   */
  public CGTUser incrementSigninAttempts(final CGTUser user) throws IllegalArgumentException;

  /**
   * Updates the last signin datetime for the specified {@link CGTUser}.
   *
   * @param user
   *     The {@link CGTUser} to update the last signed in datetime for.
   *
   * @return The {@link CGTUser} that has been updated accordingly.
   *
   * @throws IllegalArgumentException
   *     if <code>user</code> is null
   */
  public CGTUser updateLastSigninDatetime(final CGTUser user) throws IllegalArgumentException;

  /**
   * Changes the specified {@link CGTUser CGTUser's} password.
   *
   * @param user
   *     The {@link CGTUser} who's password we are changing.
   * @param password
   *     The {@link String Password} to change to.
   *
   * @return A new {@link CGTUser} with it's password changed.
   *
   * @throws IllegalArgumentException
   *     if <code>user</code> is null or <code>password</code> is null or empty
   */
  public CGTUser changeUserPassword(final CGTUser user, String password) throws IllegalArgumentException;
}
