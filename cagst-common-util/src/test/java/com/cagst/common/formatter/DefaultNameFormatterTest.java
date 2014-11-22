package com.cagst.common.formatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for the {@link DefaultNameFormatter} class.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
@RunWith(JUnit4.class)
public class DefaultNameFormatterTest {
  private final DefaultNameFormatter formatter = new DefaultNameFormatter();

  /**
   * Test the formatFullName method.
   */
  @Test
  public void testFormatFullName() {
    String test1 = formatter.formatFullName("Gaskill", "Craig", null);
    assertEquals(test1, "Gaskill, Craig");

    String test2 = formatter.formatFullName("Gaskill", null, null);
    assertEquals(test2, "Gaskill");

    String test3 = formatter.formatFullName(null, "Craig", null);
    assertEquals(test3, "Craig");

    String test4 = formatter.formatFullName(null, null, null);
    assertNotNull(test4);
    assertTrue(StringUtils.isEmpty(test4));
  }

  /**
   * Test the formatNameKey method.
   */
  @Test
  public void testFormatNameKey() {
    String test1 = formatter.formatNameKey("Gaskill");
    assertEquals(test1, "GASKILL");

    String test2 = formatter.formatNameKey("Craig Gaskill");
    assertEquals(test2, "CRAIGGASKILL");

    String test3 = formatter.formatNameKey("Craig à Gaskill");
    assertEquals(test3, "CRAIGAGASKILL");
  }
}
