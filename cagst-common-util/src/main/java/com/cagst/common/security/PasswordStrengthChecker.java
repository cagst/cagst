package com.cagst.common.security;

/**
 * Definition that will check a password to determine it's strength (how secure the password is).
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public interface PasswordStrengthChecker {
	/**
	 * @return The maximum strength this checker will return.
	 */
	public int maxStrength();

	/**
	 * The strength of the specified password.
	 * 
	 * @param password
	 *          The {@link String} password to check the strength of.
	 * 
	 * @return The strength of the specified password.
	 */
	public int checkStrength(final String password);
}
