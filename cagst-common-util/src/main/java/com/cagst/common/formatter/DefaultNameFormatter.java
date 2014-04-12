package com.cagst.common.formatter;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * Default implementation of the {@link NameFormatter} interface.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class DefaultNameFormatter implements NameFormatter, Serializable {
	private static final long serialVersionUID = -5816928639495987674L;

	/**
	 * Returns a full name to display to the user based upon the name parts.
	 * 
	 * Will attempt to return the name in the following format: (lastName), (firstName).
	 * 
	 * If the {@link lastName} is null or empty then the {@link firstName} will be returned. If the
	 * {@link firstName} is null or empty then the {@link lastName} will be returned. If both the
	 * {@link lastName} and {@link firstName} are null or empty the an empty string will be returned.
	 */
	@Override
	public String formatFullName(final String lastName, final String firstName, final String middleName) {
		if (StringUtils.isEmpty(firstName)) {
			if (StringUtils.isEmpty(lastName)) {
				return StringUtils.EMPTY;
			} else {
				return lastName;
			}
		} else {
			if (StringUtils.isEmpty(lastName)) {
				return firstName;
			} else {
				return new StringBuilder(lastName).append(", ").append(firstName).toString();
			}
		}
	}

	/**
	 * Generates a key for the specified name using the following rules:
	 * 
	 * <ol>
	 * <li>Strips on any accent characters.</li>
	 * <li>Removes any whitespace characters.</li>
	 * <li>Converts the remaining characters to upper-case.</li>
	 * </ol>
	 * 
	 * For example: <code>
	 * name = 'Gaskill' will return 'GASKILL'
	 * name = 'Craig Gaskill' will return 'CRAIGGASKILL'
	 * </code>
	 */
	@Override
	public String formatNameKey(final String name) {
		return StringUtils.upperCase(StringUtils.deleteWhitespace(StringUtils.stripAccents(name)));
	}

}
