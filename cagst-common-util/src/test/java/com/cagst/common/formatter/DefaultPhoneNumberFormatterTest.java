package com.cagst.common.formatter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for the {@link DefaultPhoneNumber} class.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
@RunWith(JUnit4.class)
public class DefaultPhoneNumberFormatterTest {
  private final DefaultPhoneNumberFormatter formatter = new DefaultPhoneNumberFormatter();

  /**
   * Test the formatPhoneNumber where there is no extension.
   */
  @Test
  public void testFormatPhoneNumber_WithoutExtension() {
    assertEquals("Ensure the number is formatter correctly (4 digits).", "5309",
        formatter.formatPhoneNumber("5309", null));

    assertEquals("Ensure the number is formatted correctly (7 digits).", "867-5309",
        formatter.formatPhoneNumber("8675309", null));

    assertEquals("Ensure the number is formatted correctly (10 digits).", "(555) 867-5309",
        formatter.formatPhoneNumber("5558675309", null));

    assertEquals("Ensure the number is formatted correctly (11 digits).", "1 (555) 867-5309",
        formatter.formatPhoneNumber("15558675309", null));
  }

  /**
   * Test the formatPhoneNumber where there is an extension.
   */
  @Test
  public void testFormatPhoneNumber_WithExtension() {
    assertEquals("Ensure the number is formatter correctly (4 digits).", "5309 x123",
        formatter.formatPhoneNumber("5309", "123"));

    assertEquals("Ensure the number is formatted correctly (7 digits).", "867-5309 x123",
        formatter.formatPhoneNumber("8675309", "123"));

    assertEquals("Ensure the number is formatted correctly (10 digits).", "(555) 867-5309 x123",
        formatter.formatPhoneNumber("5558675309", "123"));

    assertEquals("Ensure the number is formatted correctly (11 digits).", "1 (555) 867-5309 x123",
        formatter.formatPhoneNumber("15558675309", "123"));
  }
}
