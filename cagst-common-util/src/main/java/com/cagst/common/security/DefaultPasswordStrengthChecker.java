package com.cagst.common.security;

import org.apache.commons.lang3.StringUtils;

/**
 * Default implementation of the {@link PasswordStrengthChecker} interface.
 * <p/>
 * Strength is determined by the following criteria:
 * <ul>
 * <li>1 Point - Greater than or equal to min characters.</li>
 * <li>1 Point - Mixed case (upper and lower).</li>
 * <li>1 Point - Multiple mixed case (multiple upper and multiple lower).</li>
 * <li>1 Point - Alphanumeric (letters and numbers).</li>
 * <li>1 Point - Multiple alphanumeric (multiple letters and multiple numbers).</li>
 * <li>1 Point - Special characters (i.e. #, $, @).</li>
 * <li>1 Point - Multiple special characters.</li>
 * </ul>
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class DefaultPasswordStrengthChecker implements PasswordStrengthChecker {
  // Strength types (Very Weak, Weak, Fair, Average, Good, Strong)
  private final int MAX_STRENGTH = 7;

  private int minCharacters = 8;

  /**
   * @return The minimum number of characters before a security point is granted.
   */
  public int getMinCharacters() {
    return minCharacters;
  }

  /**
   * Sets the minimum number of characters a password should have before a security point is
   * granted.
   *
   * @param minCharacters
   */
  public void setMinCharacters(final int minCharacters) {
    this.minCharacters = minCharacters;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.utils.PasswordStrengthChecker#maxStrength()
   */
  @Override
  public int maxStrength() {
    return MAX_STRENGTH;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.utils.PasswordStrengthChecker#checkStrength(java.lang.String)
   */
  @Override
  public int checkStrength(final String password) {
    int strength = 0;

    if (StringUtils.isEmpty(password)) {
      return strength;
    }

    int lowerCaseChars = 0;
    int upperCaseChars = 0;
    int alphaChars = 0;
    int numericChars = 0;
    int specialChars = 0;

    // analyze password
    int cnt = password.length();
    for (int idx = 0; idx < cnt; idx++) {
      if (Character.isLowerCase(password.charAt(idx))) {
        lowerCaseChars++;
      }
      if (Character.isUpperCase(password.charAt(idx))) {
        upperCaseChars++;
      }
      if (Character.isLetter(password.charAt(idx))) {
        alphaChars++;
      }
      if (Character.isDigit(password.charAt(idx))) {
        numericChars++;
      }
      if (!Character.isLetterOrDigit(password.charAt(idx))) {
        specialChars++;
      }
    }

    // determine our strength
    if (StringUtils.length(password) >= getMinCharacters()) {
      strength++;
    }
    if (lowerCaseChars > 0 && upperCaseChars > 0) {
      strength++;
      if (lowerCaseChars > 1 && upperCaseChars > 1) {
        strength++;
      }
    }
    if (alphaChars > 0 && numericChars > 0) {
      strength++;
      if (alphaChars > 1 && numericChars > 1) {
        strength++;
      }
    }
    if (specialChars > 0) {
      strength++;
      if (specialChars > 1) {
        strength++;
      }
    }

    return strength;
  }

}
