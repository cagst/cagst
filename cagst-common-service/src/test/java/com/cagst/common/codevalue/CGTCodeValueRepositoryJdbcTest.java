package com.cagst.common.codevalue;

import static org.junit.Assert.*;

import javax.sql.DataSource;
import java.util.List;

import com.cagst.common.db.DataSourceFactory;
import com.cagst.common.person.CGTClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Test class for CGTCodeValueRepositoryJdbc.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
@RunWith(JUnit4.class)
public class CGTCodeValueRepositoryJdbcTest {
  private CGTCodeValueRepositoryJdbc repo;

  @Before
  public void setUp() {
    repo = new CGTCodeValueRepositoryJdbc(createTestDataSource());
  }

  /**
   * Test the method getCodeSetByUID and not finding the CodeSet.
   */
  @Test
  public void testGetCodeSetByUID_NotFound() {
    CGTCodeSet codeset = repo.getCodeSetByUID(999);
    assertNull("No codeset found!", codeset);
  }

  /**
   * Test the method getCodeSetByUID and finding the CodeSet.
   */
  @Test
  public void testGetCodeSetByUID_Found() {
    CGTCodeSet codeset = repo.getCodeSetByUID(3L);
    assertNotNull("No codeset found!", codeset);
    assertEquals("EMAIL_TYPE", codeset.getMeaning());
  }

  /**
   * Test the method getCodeValuesForCodeSet and not finding any CodeValue(s).
   */
  @Test
  public void testGetCodeValuesForCodeSet_NotFound() {
    CGTCodeSet codeset = repo.getCodeSetByUID(4L);
    assertNotNull("No codeset found!", codeset);
    assertEquals("DOCUMENT_TYPE", codeset.getMeaning());

    List<CGTCodeValue> codevalues = repo.getCodeValuesForCodeSet(codeset, null);
    assertNotNull("Should not return NULL!", codevalues);
    assertTrue(codevalues.isEmpty());
  }

  /**
   * Test the method getCodeValuesForCodeSet and finding CodeValue(s) for no specific Client.
   */
  @Test
  public void testGetCodeValuesForCodeSet_Found_NoClient() {
    CGTCodeSet codeset = repo.getCodeSetByUID(2L);
    assertNotNull("No codeset found!", codeset);
    assertEquals("PHONE_TYPE", codeset.getMeaning());

    List<CGTCodeValue> codevalues = repo.getCodeValuesForCodeSet(codeset, null);
    assertNotNull("Should not return NULL!", codevalues);
    assertFalse(codevalues.isEmpty());
    assertEquals(3, codevalues.size());
  }

  /**
   * Test the method getCodeValuesForCodeSet and finding CodeValue(s) for specific Client.
   */
  @Test
  public void testGetCodeValuesForCodeSet_Found_Client() {
    CGTCodeSet codeset = repo.getCodeSetByUID(2L);
    assertNotNull("No codeset found!", codeset);
    assertEquals("PHONE_TYPE", codeset.getMeaning());

    CGTClient mockClient = Mockito.mock(CGTClient.class);
    Mockito.when(mockClient.getClientUID()).thenReturn(1L);

    List<CGTCodeValue> codevalues = repo.getCodeValuesForCodeSet(codeset, mockClient);
    assertNotNull("Should not return NULL!", codevalues);
    assertFalse(codevalues.isEmpty());
    assertEquals(5, codevalues.size());
  }

  private DataSource createTestDataSource() {
    Resource schemaLocation = new ClassPathResource("/testDb/schema.sql");
    Resource testDataLocation = new ClassPathResource("/testDb/test_data.sql");

    DataSourceFactory dsFactory = new DataSourceFactory("savedb", new Resource[] {schemaLocation, testDataLocation});
    return dsFactory.getDataSource();
  }
}
