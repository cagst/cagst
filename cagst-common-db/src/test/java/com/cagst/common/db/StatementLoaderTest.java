package com.cagst.common.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the {@link StatementLoader} class.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class StatementLoaderTest {
  /**
   * Test {@link StatementLoader} to ensure we maintain the line breaks.
   */
  @Test
  public void testWithNewLines() {
    StatementLoader loader = StatementLoader.getLoader(StatementLoaderTest.class, StatementLoader.HSQLDB_DIALECT);
    String stmt = loader.load("select_with_newlines");

    assertNotNull("Ensure we have a valid statement.", stmt);
    assertFalse("Ensure the statement has content.", stmt.isEmpty());

    // split each line into a separate string
    String[] lines = stmt.split("\n");

    assertEquals("Ensure we have the correct number of strings.", 2, lines.length);
    assertEquals("select *", lines[0]);
    assertEquals("from person", lines[1]);
  }

  /**
   * Test {@link StatementLoader} to ensure inline comments are not added unless supported.
   */
  @Test
  public void testInline_NotSupported() {
    StatementLoader loader = StatementLoader.getLoader(StatementLoader.class, StatementLoader.HSQLDB_DIALECT);
    String stmt = loader.load("dialect_specific");

    assertNotNull("Ensure we have a valid statement.", stmt);
    assertFalse("Ensure the statement has content.", stmt.isEmpty());
    assertFalse("Ensure inline comments were not added.", stmt.startsWith("/*+ JDBC"));
  }

  /**
   * Test {@link StatementLoader} to ensure inline comments are added if supported.
   */
  @Test
  public void testInline_Supported() {
    StatementLoader loader = StatementLoader.getLoader(StatementLoader.class, StatementLoader.ORACLE_DIALECT);
    String stmt = loader.load("dialect_specific");

    assertNotNull("Ensure we have a valid statement.", stmt);
    assertFalse("Ensure the statement has content.", stmt.isEmpty());
    assertTrue("Ensure inline comments were added.", stmt.startsWith("/*+ JDBC"));
  }

  /**
   * Test {@link StatementLoader} to ensure dialect specific statements are loaded.
   */
  @Test
  public void testDialectSpecific() {
    StatementLoader loader = StatementLoader.getLoader(StatementLoader.class, "derby");
    String stmt = loader.load("dialect_specific");

    assertNotNull("Ensure we have a valid statement.", stmt);
    assertFalse("Ensure the statement has content.", stmt.isEmpty());
    assertEquals("Ensure loaded the dialect specific statement.", "select * from derby_person\n", stmt);
  }

  /**
   * Test {@link StatementLoader} to ensure dialect-independent statements are loaded.
   */
  @Test
  public void testDialectIndependent() {
    StatementLoader loader = StatementLoader.getLoader(StatementLoader.class, StatementLoader.HSQLDB_DIALECT);
    String stmt = loader.load("dialect_specific");

    assertNotNull("Ensure we have a valid statement.", stmt);
    assertFalse("Ensure the statement has content.", stmt.isEmpty());
    assertEquals("Ensure loaded the dialect independent statement.", "select * from person\n", stmt);
  }
}
