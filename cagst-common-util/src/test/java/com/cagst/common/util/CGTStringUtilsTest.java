package com.cagst.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for the CGTStringUtils class.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
@RunWith(JUnit4.class)
public class CGTStringUtilsTest {
  @Test
  public void testNormalizeName() {
    String ns = CGTStringUtils.normalizeToKey(null);
    assertNull("Ensure the normalized name is null.", ns);

    String es = CGTStringUtils.normalizeToKey(StringUtils.EMPTY);
    assertNotNull("Ensure the normalized name is not null.", es);
    assertTrue("Ensure the normalized name is empty.", es.isEmpty());

    String nn = CGTStringUtils.normalizeToKey("Gaskill");
    assertNotNull("Ensure the normalized name is not null.", nn);
    assertEquals("Ensure the name was normalized properly.", "GASKILL", nn);

    String an = CGTStringUtils.normalizeToKey("Gàskill");
    assertNotNull("Ensure the normalized name is not null.", an);
    assertEquals("Ensure the name was normalized properly.", "GASKILL", an);
  }
}
