package com.cagst.common.person;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * A mutable representation of a User within the system.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class CGTUserBuilder extends CGTPersonBuilder {
  private static final long serialVersionUID = 459919042550669570L;

  private static final DateTimeComparator comparator = DateTimeComparator.getInstance();

  private long user_id;
  private String username;
  private String password;
  private DateTime expire_dt_tm;
  private DateTime last_signin_dt_tm;
  private int signin_attempts;
  private boolean change_password;
  private DateTime account_locked_dt_tm;
  private long updt_cnt = 0;

  private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

  private final CGTUser originalUser;

  /**
   * Primary Constructor used to create an instance of a CGTUserBuilder.
   */
  public CGTUserBuilder() {
    this.originalUser = null;
  }

  /**
   * Primary Constructor used to create an instance of CGTUserBuilder from an existing instance of a
   * {@link CGTUser}.
   *
   * @param user
   *     The {@link CGTUser} to base this CGTUserBuilder off of.
   */
  public CGTUserBuilder(final CGTUser user) {
    super(user);

    copyFrom(user);

    this.originalUser = user;
  }

  /**
   * Gets the unique identifier for the User object.
   *
   * @return A {@link long} that uniquely identifier for the User.
   */
  public long getUserUID() {
    return user_id;
  }

  /**
   * Sets the unique identifier for the User object.
   *
   * @param userUID
   *     {@link long} the unique identifier for the User.
   */
  public void setUserUID(final long userUID) {
    this.user_id = userUID;
  }

  /**
   * Gets the username associated to the User.
   *
   * @return The {@link String} username for the User.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username associated to the User.
   *
   * @param username
   *     The {@link String} username to associate to this User.
   */
  public void setUsername(final String username) {
    this.username = username;
  }

  /**
   * Gets the password associated to the User.
   *
   * @return The {@link String} password for the User.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password associated to the User.
   *
   * @param password
   *     The {@link String} password to associate to this User.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Gets the date this user account expires.
   *
   * @return The {@link DateTime} this user account expires.
   */
  public DateTime getExpireDate() {
    return expire_dt_tm;
  }

  /**
   * Sets the date this user account will expire.
   *
   * @param expireDate
   *     The {@link DateTime} this user account will expire.
   */
  public void setExpireDate(final DateTime expireDate) {
    this.expire_dt_tm = expireDate;
  }

  /**
   * Gets the date this user last signed in.
   *
   * @return The {@link DateTime} this user last signed in.
   */
  public DateTime getLastSigninDate() {
    return last_signin_dt_tm;
  }

  /**
   * Sets the date this user last signed in.
   *
   * @param lastSignin
   *     The {@link DateTime} this user lasted signed in.
   */
  public void setLastSigninDate(final DateTime lastSignin) {
    this.last_signin_dt_tm = lastSignin;
  }

  /**
   * Gets the number of times this user has attempted to sign in but failed to do so.
   *
   * @return The number of times this user has attempted to sign in.
   */
  public int getSigninAttempts() {
    return signin_attempts;
  }

  /**
   * Sets the number of times this user has attempted to sign in but failed to do so.
   *
   * @param signinAttempts
   *     The number of times this user has attempted to sign in.
   */
  public void setSigninAttempts(final int signinAttempts) {
    this.signin_attempts = signinAttempts;
  }

  /**
   * @return <code>true</code> if the user needs to change their password.
   */
  public boolean needToChangePassword() {
    return change_password;
  }

  /**
   * Sets whether the user should change their password the next time they sign in.
   *
   * @param changePwd
   *     <code>true</code> if the user should change their password the next time they sign in.
   */
  public void setChangePassword(final boolean changePwd) {
    this.change_password = changePwd;
  }

  /**
   * @return The date this account was locked.
   */
  public DateTime getAccountLockedDate() {
    return account_locked_dt_tm;
  }

  /**
   * Sets the date this account was locked.
   *
   * @param accountLockedDate
   *     The {@link DateTime} this account was locked.
   */
  public void setAccountedLockedDate(final DateTime accountLockedDate) {
    this.account_locked_dt_tm = accountLockedDate;
  }

  public boolean isAccountNonExpired() {
    if (expire_dt_tm == null) {
      return true;
    }

    return (comparator.compare(expire_dt_tm, new DateTime()) > 0);
  }

  public boolean isAccountNonLocked() {
    return (account_locked_dt_tm == null);
  }

  public boolean isCredentialsNonExpired() {
    if (expire_dt_tm == null) {
      return true;
    }

    return (comparator.compare(expire_dt_tm, new DateTime()) > 0);
  }

  public boolean isEnabled() {
    return true;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void addGrantedAuthority(final GrantedAuthority authority) {
    authorities.add(authority);
  }

  public void addGrantedAuthority(final String authority) {
    authorities.add(new SimpleGrantedAuthority(authority));
  }

  /**
   * Gets the number of times this object has been updated.
   *
   * @return {@link long} number of times the object has been updated.
   */
  public long getUserUpdateCount() {
    return updt_cnt;
  }

  /**
   * Sets the number of times this object has been updated.
   *
   * @param updateCount
   *     {@link long} the number of times the object has been updated.
   */
  public void setUserUpdateCount(final long updateCount) {
    this.updt_cnt = updateCount;
  }

  @Override
  public void rejectChanges() {
    if (originalUser != null) {
      copyFrom(originalUser);
    } else {
      this.username = null;
      this.expire_dt_tm = null;
      this.last_signin_dt_tm = null;
      this.signin_attempts = 0;
      this.change_password = false;
      this.account_locked_dt_tm = null;
    }

    super.rejectChanges();
  }

  @Override
  public boolean hasChanged(final boolean deepCheck) {
    if (originalUser == null) {
      return true;
    }

    EqualsBuilder builder = new EqualsBuilder();
    builder.append(username, originalUser.getUsername());
    builder.append(expire_dt_tm, originalUser.getExpireDate());
    builder.append(isActive(), originalUser.isActive());

    if (deepCheck) {
      builder.appendSuper(!super.hasChanged(deepCheck));
    }

    return !builder.build();
  }

  @Override
  public boolean isValid() {
    return !StringUtils.isEmpty(username) && super.isValid();
  }

  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
    builder.append("username", username);
    builder.append("expire_dt_tm", expire_dt_tm);
    builder.append("last_signin_dt_tm", last_signin_dt_tm);
    builder.append("signin_attempts", signin_attempts);
    builder.append("change_pwd_ind", change_password);
    builder.append("account_locked_dt_tm", account_locked_dt_tm);
    builder.append("updateCount", updt_cnt);
    builder.appendSuper(super.toString());

    return builder.build();
  }

  /**
   * Builds a {@link CGTUser} based upon this builder.
   *
   * @return An immutable {@link CGTUser}.
   */
  @Override
  public CGTUser build() {
    return new CGTUser(this);
  }

  protected void copyFrom(final CGTUser user) {
    this.user_id = user.getUserUID();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.expire_dt_tm = user.getExpireDate();
    this.last_signin_dt_tm = user.getLastSigninDate();
    this.signin_attempts = user.getSigninAttempts();
    this.change_password = user.needToChangePassword();
    this.account_locked_dt_tm = user.getAccountLockedDate();
    this.updt_cnt = user.getUserUpdateCount();
    this.authorities = new ArrayList<GrantedAuthority>(user.getAuthorities());
  }
}
