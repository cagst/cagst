package com.cagst.common.formatter;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * Formatter definition that will return the Age as a {@link String} for display.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public interface AgeFormatter {
	/**
	 * Returns the current age based upon the specified start date.
	 * 
	 * @param startDate
	 *          The {@link DateTime} to start the age calculation from.
	 * 
	 * @return
	 */
	public String formatAge(final DateTime startDate);

	/**
	 * Returns the current age based upon the specified start date.
	 * 
	 * @param startDate
	 *          The {@link Date} to start the age calculation from.
	 * 
	 * @return
	 */
	public String formatAge(final Date startDate);

	/**
	 * Returns the age based upon the specified start / stop date.
	 * 
	 * @param startDate
	 *          The {@link DateTime} to start the age calculation from.
	 * @param stopDate
	 *          The {@link DateTime} to stop the age calculation on.
	 * 
	 * @return
	 */
	public String formatAge(final DateTime startDate, final DateTime stopDate);

	/**
	 * Returns the age based upon the specified start / stop date.
	 * 
	 * @param startDate
	 *          The {@link Date} to start the age calculation from.
	 * @param stopDate
	 *          The {@link Date} to stop the age calculation on.
	 * 
	 * @return
	 */
	public String formatAge(final Date startDate, final Date stopDate);
}
