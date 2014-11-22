package com.cagst.common.person;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Immutable class that represents a User within the system.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class CGTUser extends CGTPerson implements UserDetails {
  private static final long serialVersionUID = -8751337547512595799L;

  private static final DateTimeComparator comparator = DateTimeComparator.getInstance();

  private final long user_id;
  private final String username;
  private final String password;
  private final DateTime expire_dt_tm;
  private final DateTime last_signin_dt_tm;
  private final int signin_attempts;
  private final boolean change_password;
  private final DateTime account_locked_dt_tm;
  private final long updt_cnt;

  private final Collection<GrantedAuthority> authorities;

  /**
   * Primary Constructor used to create an instance of a CGTUser from an existing
   * {@link CGTUserBuilder}.
   *
   * @param builder
   *     The {@link CGTUserBuilder} to base this CGTUser off of.
   */
  /* package */CGTUser(final CGTUserBuilder builder) {
    super(builder);

    this.user_id = builder.getUserUID();
    this.username = builder.getUsername();
    this.password = builder.getPassword();
    this.expire_dt_tm = builder.getExpireDate();
    this.last_signin_dt_tm = builder.getLastSigninDate();
    this.signin_attempts = builder.getSigninAttempts();
    this.change_password = builder.needToChangePassword();
    this.account_locked_dt_tm = builder.getAccountLockedDate();
    this.updt_cnt = builder.getUserUpdateCount();

    this.authorities = new ArrayList<GrantedAuthority>(builder.getAuthorities());
  }

  /**
   * Gets the unique identifier for the User.
   *
   * @return A {@link long} that uniquely identifies the User.
   */
  public long getUserUID() {
    return user_id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /**
   * @return The {@link DateTime} this user account has/will expires.
   */
  public DateTime getExpireDate() {
    return expire_dt_tm;
  }

  /**
   * @return <code>true</code> if the user needs to change their password.
   */
  public boolean needToChangePassword() {
    return change_password;
  }

  /**
   * @return The {@link DateTime} this user last signed in.
   */
  public DateTime getLastSigninDate() {
    return last_signin_dt_tm;
  }

  /**
   * @return The number of times this user has failed to sign in.
   */
  public int getSigninAttempts() {
    return signin_attempts;
  }

  /**
   * @return The date this account was locked.
   */
  public DateTime getAccountLockedDate() {
    return account_locked_dt_tm;
  }

  @Override
  public boolean isAccountNonExpired() {
    if (expire_dt_tm == null) {
      return true;
    }

    return (comparator.compare(expire_dt_tm, new DateTime()) > 0);
  }

  @Override
  public boolean isAccountNonLocked() {
    return (account_locked_dt_tm == null);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    if (expire_dt_tm == null) {
      return true;
    }

    return (comparator.compare(expire_dt_tm, new DateTime()) > 0);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  /**
   * Gets the number of times this object has been updated.
   *
   * @return {@link long} number of times the object has been updated.
   */
  public final long getUserUpdateCount() {
    return updt_cnt;
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

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.appendSuper(super.hashCode());
    builder.append(username);

    return builder.build();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof CGTUser)) {
      return false;
    }

    CGTUser rhs = (CGTUser) obj;

    EqualsBuilder builder = new EqualsBuilder();
    builder.appendSuper(super.equals(obj));
    builder.append(username, rhs.getUsername());

    return builder.build();
  }
}
