package com.cagst.common.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for the {@link DefaultPasswordStrengthChecker} class.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
@RunWith(JUnit4.class)
public class DefaultPasswordStrengthCheckerTest {
  private final DefaultPasswordStrengthChecker strengthChecker = new DefaultPasswordStrengthChecker();

  @Test
  public void testPassword_None() {
    assertEquals(0, strengthChecker.checkStrength(null));
  }

  @Test
  public void testPassword_Length() {
    assertEquals(0, strengthChecker.checkStrength("12"));
    assertEquals(0, strengthChecker.checkStrength("1234567"));
    assertEquals(1, strengthChecker.checkStrength("12345678"));
    assertEquals(1, strengthChecker.checkStrength("123456789"));
  }

  @Test
  public void testPassword_Alpha() {
    assertEquals(0, strengthChecker.checkStrength("abc"));
    assertEquals(0, strengthChecker.checkStrength("abcdefg"));
    assertEquals(1, strengthChecker.checkStrength("abcdefgh"));
    assertEquals(1, strengthChecker.checkStrength("abcdefghi"));
  }

  @Test
  public void testPassword_Numeric() {
    assertEquals(0, strengthChecker.checkStrength("12"));
    assertEquals(0, strengthChecker.checkStrength("1234567"));
    assertEquals(1, strengthChecker.checkStrength("12345678"));
    assertEquals(1, strengthChecker.checkStrength("123456789"));
  }

  @Test
  public void testPassword_AlphaNumeric() {
    // alphanumeric only
    assertEquals(1, strengthChecker.checkStrength("a123"));

    // alphanumeric, multiple alphanumeric
    assertEquals(2, strengthChecker.checkStrength("abc123"));

    // alphanumeric, multiple alphanumeric, greater than min
    assertEquals(3, strengthChecker.checkStrength("abcd1234"));
  }

  @Test
  public void testPassword_Special() {
    // special only
    assertEquals(1, strengthChecker.checkStrength("a@"));

    // special, multiple special
    assertEquals(2, strengthChecker.checkStrength("a$$"));

    // special, multiple special, alphanumeric
    assertEquals(3, strengthChecker.checkStrength("a$$1"));

    // special, multiple special, alphanumeric, multiple alphanumeric
    assertEquals(4, strengthChecker.checkStrength("a$$1b2"));
  }

  @Test
  public void testPassword_Case() {
    // no mixed case
    assertEquals(0, strengthChecker.checkStrength("abc"));

    // no mixed case
    assertEquals(0, strengthChecker.checkStrength("ABC"));

    // mixed case only
    assertEquals(1, strengthChecker.checkStrength("aBc"));

    // mixed case, multiple mixed case
    assertEquals(2, strengthChecker.checkStrength("aBcD"));

    // mixed case, multiple mixed case, alpha numeric
    assertEquals(3, strengthChecker.checkStrength("a1BcD"));

    // mixed case, multiple mixed case, alpha numeric, multiple alphanumeric
    assertEquals(4, strengthChecker.checkStrength("a1B2cD"));
  }

  @Test
  public void testPassword_NoPoint() {
    assertEquals(0, strengthChecker.checkStrength("pass"));
  }

  @Test
  public void testPassword_1Point() {
    assertEquals(1, strengthChecker.checkStrength("Passw"));
  }

  @Test
  public void testPassword_2Point() {
    assertEquals(2, strengthChecker.checkStrength("Password"));
  }

  @Test
  public void testPassword_3Point() {
    assertEquals(3, strengthChecker.checkStrength("Passw0rd"));
  }

  @Test
  public void testPassword_4Point() {
    assertEquals(4, strengthChecker.checkStrength("P@ssw0rd"));
  }

  @Test
  public void testPassword_5Point() {
    assertEquals(5, strengthChecker.checkStrength("P@ssw0rd!"));
  }

  @Test
  public void testPassword_6Point() {
    assertEquals(6, strengthChecker.checkStrength("P@ssw0rD!"));
  }

  @Test
  public void testPassword_7Point() {
    assertEquals(7, strengthChecker.checkStrength("P@ssw0rD!2"));
  }
}
