package com.cagst.common.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

import com.cagst.common.person.CGTUser;
import com.cagst.common.person.CGTUserBuilder;
import com.cagst.common.user.CGTUserRepository;

/**
 * User Detail Service that provides authentication for Save Inc.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTUserDetailsService implements UserDetailsManager {
	@Autowired
	private CGTUserRepository userRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang
	 * .String)
	 */
	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException, DataAccessException {
		CGTUser user = userRepo.getUserByUsername(userName);
		if (user != null) {
			CGTUser newUser = userRepo.incrementSigninAttempts(user);

			CGTUserBuilder builder = new CGTUserBuilder(newUser);
			builder.addGrantedAuthority("ROLE_USER");

			return builder.build();
		}

		// not allowed to return NULL
		CGTUserBuilder builder = new CGTUserBuilder();
		return builder.build();
	}

	@Override
	public void createUser(final UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(final UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(final String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(final String oldPassword, final String newPassword) {
		Assert.hasLength(oldPassword, "oldPassword cannot be null or empty");
		Assert.hasLength(newPassword, "newPassword cannot be null or empty");

		if (StringUtils.equals(newPassword, newPassword)) {
			return;
		}

		// TODO Auto-generated method stub

	}

	@Override
	public boolean userExists(final String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
