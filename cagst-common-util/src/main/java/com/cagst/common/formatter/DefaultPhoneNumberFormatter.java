package com.cagst.common.formatter;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * Default implementation of the {@link PhoneNumberFormatter} interface.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class DefaultPhoneNumberFormatter implements PhoneNumberFormatter, Serializable {
	private static final long serialVersionUID = 5326976728997407118L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cagst.common.formatter.PhoneFormatter#formatPhoneNumber(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String formatPhoneNumber(final String phoneNumber, final String extension) {
		if (StringUtils.isEmpty(extension)) {
			return formatPhoneNumber(phoneNumber);
		} else {
			return (formatPhoneNumber(phoneNumber) + " x" + extension);
		}
	}

	private String formatPhoneNumber(final String phoneNumber) {
		if (StringUtils.length(phoneNumber) == 11) {
			return String.format("%s (%s) %s-%s", StringUtils.left(phoneNumber, 1), StringUtils.mid(phoneNumber, 1, 3),
					StringUtils.mid(phoneNumber, 4, 3), StringUtils.right(phoneNumber, 4));
		} else if (StringUtils.length(phoneNumber) == 10) {
			return String.format("(%s) %s-%s", StringUtils.left(phoneNumber, 3), StringUtils.mid(phoneNumber, 3, 3),
					StringUtils.right(phoneNumber, 4));
		} else if (StringUtils.length(phoneNumber) == 7) {
			return String.format("%s-%s", StringUtils.left(phoneNumber, 3), StringUtils.right(phoneNumber, 4));
		} else {
			return phoneNumber;
		}
	}
}
