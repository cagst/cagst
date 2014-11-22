package com.cagst.common.db;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * A factory that creates a data source fit for use in a system test environment. Creates a simple
 * data source that connects to an in-memory database pre-loaded with test data.
 * <p/>
 * This factory returns a fully-initialized DataSource implementation. When the DataSource is
 * returned, callers are guaranteed that the database schema and test data will have been loaded by
 * that time.
 * <p/>
 * Is a FactoryBean, for exposing the fully-initialized test DataSource as a Spring bean. See
 * {@link #getObject()}.
 */
public final class DataSourceFactory implements FactoryBean<DataSource> {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);

  /**
   * The object created by this factory *
   */
  private DataSource dataSource;

  private final String testDatabaseName;
  private final Resource schemaLocation;
  private final Resource alternateSchemaLocation;
  private final Resource testDataLocation;

  /**
   * Creates a new TestDataSourceFactory fully-initialized with what it needs to work. Fully-formed
   * constructors are nice in a programmatic environment, as they result in more concise code and
   * allow for a class to enforce its required properties.
   *
   * @param testDatabaseName
   *    The name of the test database to create
   * @param schemaLocation
   *    The location of the file containing the schema DDL to import to the database
   * @param testDataLocation
   *    The location of the file containing the test data to load into the database
   */
  public DataSourceFactory(final String testDatabaseName,
                           final Resource schemaLocation,
                           final Resource testDataLocation) {
    this(testDatabaseName, schemaLocation, null, testDataLocation);
  }

  /**
   * Creates a new TestDataSourceFactory fully-initialized with what it needs to work. Fully-formed
   * constructors are nice in a programmatic environment, as they result in more concise code and
   * allow for a class to enforce its required properties.
   *
   * @param testDatabaseName
   *    The name of the test database to create
   * @param schemaLocation
   *    The location of the file containing the schema DDL to import to the database
   * @param alternateSchemaLocation
   *    The location of the file containing alternate schema DDL (like a view) to import into the database.
   * @param testDataLocation
   *     the location of the file containing the test data to load into the database
   */
  public DataSourceFactory(final String testDatabaseName,
                           final Resource schemaLocation,
                           final Resource alternateSchemaLocation,
                           final Resource testDataLocation) {
    this.testDatabaseName = testDatabaseName;
    this.schemaLocation = schemaLocation;
    this.alternateSchemaLocation = alternateSchemaLocation;
    this.testDataLocation = testDataLocation;
  }

  public DataSource getObject() throws Exception {
    return getDataSource();
  }

  public Class<?> getObjectType() {
    return DataSource.class;
  }

  public boolean isSingleton() {
    return true;
  }

  public DataSource getDataSource() {
    if (null == dataSource) {
      initializeDataSource();
    }

    return dataSource;
  }

  /*
   * Encapsulates the steps involved in initializing the data source. <lu> <li>Create
   * DataSource</li> <li>Populate DataSource</li> </lu>
   */
  private void initializeDataSource() {
    // create the in-memory database source first
    this.dataSource = createDataSource();

    // now populate the data source
    populateDataSource();
  }

  private DataSource createDataSource() {
    logger.debug("Creating Test Data Source...");

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    // use the HSQLDB JDBC driver
    dataSource.setDriverClassName("org.hsqldb.jdbcDriver");

    // have it create an in-memory database
    dataSource.setUrl("jdbc:hsqldb:mem:" + testDatabaseName);
    dataSource.setUsername("sa");
    dataSource.setPassword("");

    return dataSource;
  }

  private void populateDataSource() {
    logger.debug("Populating Test Data Source...");

    TestDatabasePopulator populator = new TestDatabasePopulator(dataSource);
    populator.populate();
  }

  /**
   * Populates an in-memory data source with test data.
   *
   * @author Craig Gaskill
   */
  private final class TestDatabasePopulator {
    private final DataSource dataSource;

    /**
     * Primary Constructor
     *
     * @param dataSource
     *     {@link DataSource} to populate data with.
     */
    public TestDatabasePopulator(final DataSource dataSource) {
      this.dataSource = dataSource;
    }

    public void populate() {
      Connection conn = null;

      try {
        conn = dataSource.getConnection();

        createDatabaseSchema(conn);
        createAlternateDatabaseSchema(conn);
        populateTestData(conn);
      } catch (SQLException ex) {
        throw new RuntimeException("SQLException occurred", ex);
      } finally {
        if (null != conn) {
          try {
            conn.close();
          } catch (SQLException ex) {
            // ignore, just trying to close things down.
          }
        }
      }
    }

    private void createDatabaseSchema(final Connection conn) throws SQLException {
      logger.debug("Creating schema...");

      try {
        String sql = parseSqlIn(schemaLocation);
        executeSql(conn, sql);
      } catch (IOException ex) {
        throw new RuntimeException("IOException occurred creating database schema", ex);
      }
    }

    private void createAlternateDatabaseSchema(final Connection conn) throws SQLException {
      if (alternateSchemaLocation == null) {
        return;
      }

      logger.debug("Creating alternate schema...");

      try {
        String sql = parseSqlIn(alternateSchemaLocation);
        executeSql(conn, sql);
      } catch (IOException ex) {
        throw new RuntimeException("IOException occurred creating alternate database schema", ex);
      }
    }

    private void populateTestData(final Connection conn) throws SQLException {
      logger.debug("Populating test data...");

      try {
        String sql = parseSqlIn(testDataLocation);
        executeSql(conn, sql);
      } catch (IOException ex) {
        throw new RuntimeException("IOException occurred populating database with data", ex);
      }
    }

    private String parseSqlIn(final Resource resource) throws IOException {
      InputStream is = null;

      try {
        is = resource.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringWriter sw = new StringWriter();
        BufferedWriter writer = new BufferedWriter(sw);

        String line;
        while ((line = reader.readLine()) != null) {
          writer.write(line);
        }
        writer.flush();

        return sw.toString();
      } finally {
        if (null != is) {
          is.close();
        }
      }
    }

    private void executeSql(final Connection conn, final String sql) throws SQLException {
      Statement stmt = conn.createStatement();
      stmt.execute(sql);
    }
  }
}
