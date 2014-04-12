package com.cagst.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.cagst.common.person.CGTUser;
import com.cagst.common.user.CGTUserRepository;
import com.cagst.common.web.servlet.tags.StaticResourceAssistant;

/**
 * Handler class that gets called upon successful authentication. Used to determine if the user
 * needs to change their password.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private CGTUserRepository userRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#
	 * onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication auth) throws IOException, ServletException {

		if (auth.getPrincipal() != null && auth.getPrincipal() instanceof CGTUser) {
			CGTUser user = (CGTUser) auth.getPrincipal();
			userRepo.updateLastSigninDatetime(user);

			if (user.needToChangePassword()) {
				response.sendRedirect(request.getContextPath() + StaticResourceAssistant.getString("url.auth.changepwd"));
				return;
			}
		}

		super.onAuthenticationSuccess(request, response, auth);
	}

}
