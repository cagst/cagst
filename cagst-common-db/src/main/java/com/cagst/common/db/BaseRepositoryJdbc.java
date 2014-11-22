package com.cagst.common.db;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Abstract class that implements common functionality for all JDBC repository implementations.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public abstract class BaseRepositoryJdbc {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  private String stmtDialect = StatementLoader.MYSQL_DIALECT;

  /**
   * Primary Constructor used to create an instance of the BaseRepositoryJdbc.
   *
   * @param dataSource
   *     The {@link DataSource} used to retrieve / persist data objects.
   */
  public BaseRepositoryJdbc(final DataSource dataSource) {
    jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * @return The current dialect being used to retrieve SQL resource statements.
   */
  public String getStatementDialect() {
    return stmtDialect;
  }

  /**
   * Sets the dialect to use to retrieve SQL resource statements.
   *
   * @param stmtDialect
   *     The new dialect to use to retrieve SQL resource statement, "oracle" is the default.
   */
  public void setStatementDialect(final String stmtDialect) {
    this.stmtDialect = stmtDialect;
  }

  /**
   * @return The {@link NamedParameterJdbcTemplate} JDBC Template associated to this repository.
   */
  public NamedParameterJdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }
}
