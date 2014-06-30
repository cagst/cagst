package com.cagst.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.util.Assert;

/**
 * This is a utility class that will help in the conversion of a date/time column in the database that is stored as UTC
 * into a {@link DateTime} when reading from and writing to a database.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public final class CGTDateTimeUtils {
	/**
	 * Converts the specified {@link DateTime} into a {@link Timestamp}.
	 * 
	 * @param dt
	 *          The {@link DateTime} to convert.
	 * 
	 * @return A {@link Timestamp} that represents the specified {@link DateTime}, {@code null} if the DateTime is null.
	 */
	public static Timestamp convertDateTimeToTimestamp(final DateTime dt) {
		if (dt == null) {
			return null;
		}

		return new Timestamp(dt.getMillis());
	}

	/**
	 * Returns the {@link DateTime} from the specified {@link ResultSet} that is stored in the specified column.
	 * 
	 * @param rs
	 *          The {@link ResultSet} that contains the DateTime to retrieve.
	 * @param column
	 *          The index of the column specified in the SQL clause.
	 * 
	 * @return The column value; if the value is SQL {@code null}, the value return is {@code null}.
	 * 
	 * @throws SQLException
	 *           if the column is not valid; if a database access error occurs or this method is called on a closed result
	 *           set
	 */
	public static DateTime getDateTime(final ResultSet rs, final int column) throws SQLException {
		return getDateTime(rs.getTimestamp(column));
	}

	/**
	 * Returns the {@link DateTime} from the specified {@link ResultSet} that is stored in the specified column.
	 * 
	 * @param rs
	 *          The {@link ResultSet} that contains the DateTime to retrieve.
	 * @param column
	 *          The label for the column specified in the SQL AS clause. If the SQL AS clause was not specified, then the
	 *          label is the name of the column.
	 * 
	 * @return The column value; if the value is SQL {@code null}, the value return is {@code null}.
	 * 
	 * @throws SQLException
	 *           if the column is not valid; if a database access error occurs or this method is called on a closed result
	 *           set
	 */
	public static DateTime getDateTime(final ResultSet rs, final String column) throws SQLException {
		return getDateTime(rs.getTimestamp(column));
	}

	/**
	 * Returns the {@link DateTime} from the specified {@link Timestamp}.
	 *
	 * @param ts
	 * 				The {@link Timestamp} to retrieve as a {@link DateTime}.
	 *
	 * @return
	 */
	public static DateTime getDateTime(final Timestamp ts) {
		if (ts != null) {
			return new DateTime(ts);
		}

		return null;
	}

	/**
	 * Returns the {@link DateTime} from the specified {@link ResultSet} that is stored in the specified column.
	 * 
	 * @param rs
	 *          The {@link ResultSet} that contains the DateTime to retrieve.
	 * @param column
	 *          The label for the column specified in the SQL AS clause. If the SQL AS clause was not specified, then the
	 *          label is the name of the column.
	 * 
	 * @return The column value; if the value is SQL {@code null}, the value return is {@code null}.
	 * 
	 * @throws SQLException
	 *           if the column is not valid; if a database access error occurs or this method is called on a closed result
	 *           set
	 */
	public static DateTime getUTCDateTime(final ResultSet rs, final String column) throws SQLException {
		Assert.notNull(rs, "[Assertion Failed] - argument [rs] cannot be null");
		Assert.hasText(column, "[Assertion Failed] - argument [column] cannnot be null or empty");

		return getUTCDateTime(rs.getTimestamp(column));
	}

	/**
	 * Returns the {@link DateTime} from the specified {@link ResultSet} that is stored in the specified column.
	 * 
	 * @param rs
	 *          The {@link ResultSet} that contains the DateTime to retrieve.
	 * @param column
	 *          The index of the column specified in the SQL clause.
	 * 
	 * @return The column value; if the value is SQL {@code null}, the value return is {@code null}.
	 * 
	 * @throws SQLException
	 *           if the column is not valid; if a database access error occurs or this method is called on a closed result
	 *           set
	 */
	public static DateTime getUTCDateTime(final ResultSet rs, final int column) throws SQLException {
		Assert.notNull(rs, "[Assertion Failed] - argument [rs] cannot be null");

		return getUTCDateTime(rs.getTimestamp(column));
	}

	private static DateTime getUTCDateTime(final Timestamp ts) {
		if (ts == null) {
			return null;
		}

		// We use the LocaleDateTime object because it makes no assumption regarding your current time
		// zone.
		LocalDateTime utc = new LocalDateTime(ts);

		// We then trick the Joda Time libraries into using the LocalDateTime as a UTC date/time...which
		// it is.
		return new DateTime(utc.toString() + "Z");
	}
}
