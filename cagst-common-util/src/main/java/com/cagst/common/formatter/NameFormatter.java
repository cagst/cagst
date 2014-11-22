package com.cagst.common.formatter;

/**
 * Formatter definition that will return a name in various forms.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public interface NameFormatter {
  /**
   * Returns the full name for display based upon the specified parts.
   *
   * @param lastName
   *     The {@link String} last name.
   * @param firstName
   *     The {@link String} first name.
   * @param middleName
   *     The {@link String} middle name.
   *
   * @return
   */
  public String formatFullName(final String lastName, final String firstName, final String middleName);

  /**
   * Returns a {@link String} key value for the specified name.
   *
   * @param name
   *     The {@link String} name to generate key for.
   *
   * @return
   */
  public String formatNameKey(final String name);
}
