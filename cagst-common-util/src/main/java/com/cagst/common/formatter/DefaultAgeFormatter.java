package com.cagst.common.formatter;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * Default implementation of the {@link AgeFormatter} interface. Returns the age in the form of
 * years/months or months/days depending upon the duration of the age.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public final class DefaultAgeFormatter implements AgeFormatter, Serializable {
  private static final long serialVersionUID = -2850388161321956012L;

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.formatter.AgeFormatter#formatAge(org.joda.time.DateTime)
   */
  @Override
  public String formatAge(final DateTime startDate) {
    return formatAge(startDate, DateTime.now());
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.formatter.AgeFormatter#formatAge(java.util.Date)
   */
  @Override
  public String formatAge(final Date startDate) {
    return formatAge(startDate, DateTime.now().toDate());
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.formatter.AgeFormatter#formateAge(org.joda.time.DateTime,
   * org.joda.time.DateTime)
   */
  @Override
  public String formatAge(final DateTime startDate, final DateTime stopDate) {
    if (startDate == null || stopDate == null) {
      return StringUtils.EMPTY;
    }

    int years = stopDate.getYear() - startDate.getYear();
    int months = stopDate.getMonthOfYear() - startDate.getMonthOfYear();
    int days = stopDate.getDayOfMonth() - startDate.getDayOfMonth();

    // adjust the month
    if (days < 0) {
      months--;
    }

    // adjust the year
    if (months < 0) {
      years--;
      months += 12;
    }

    if (years > 0) {
      // adult age calculation
      if (months > 0) {
        return new StringBuilder().append(years).append("y ").append(months).append("m").toString();
      } else {
        return new StringBuilder().append(years).append("y").toString();
      }
    } else {
      // pediatric age calculation
      if (months > 0) {
        return new StringBuilder().append(months).append("m ").append(days).append("d").toString();
      } else {
        return new StringBuilder().append(days).append("d").toString();
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.formatter.AgeFormatter#formatAge(java.util.Date, java.util.Date)
   */
  @Override
  public String formatAge(final Date startDate, final Date stopDate) {
    if (startDate == null || stopDate == null) {
      return StringUtils.EMPTY;
    }

    DateTime start = new DateTime(startDate);
    DateTime stop = new DateTime(stopDate);

    return formatAge(start, stop);
  }
}
