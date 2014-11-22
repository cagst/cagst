package com.cagst.common.formatter;

/**
 * Formatter definition that will return a phone number in various forms.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public interface PhoneNumberFormatter {
  /**
   * Returns the phone number / extension formatted for display.
   *
   * @param phoneNumber
   *     The {@link String} phone number to format.
   * @param extension
   *     The {@link String} extension to use in the formatter.
   *
   * @return
   */
  public String formatPhoneNumber(final String phoneNumber, final String extension);
}
