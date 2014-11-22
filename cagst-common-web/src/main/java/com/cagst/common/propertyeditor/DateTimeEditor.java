package com.cagst.common.propertyeditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link PropertyEditor} that will retrieve the {@link DateTime} represented by the specified
 * string.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class DateTimeEditor extends PropertyEditorSupport {
  private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeEditor.class);

  private final DateTimeFormatter formatForWeb = DateTimeFormat.forPattern("yyyy-MM-dd");
  private final DateTimeFormatter alternateFormat = DateTimeFormat.forPattern("MM/dd/yyyy");

  /*
   * (non-Javadoc)
   *
   * @see java.beans.PropertyEditorSupport#getAsText()
   */
  @Override
  public String getAsText() {
    if (getValue() == null) {
      return StringUtils.EMPTY;
    }

    if (getValue() instanceof DateTime) {
      DateTime dt = (DateTime) getValue();
      String fmt = formatForWeb.print(dt);
      return formatForWeb.print(dt);
    }

    return StringUtils.EMPTY;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
   */
  @Override
  public void setAsText(final String text) {
    DateTime dt = null;
    try {
      dt = DateTime.parse(text, formatForWeb);
    } catch (IllegalArgumentException ex) {
      // try the alternate format
      try {
        dt = DateTime.parse(text, alternateFormat);
      } catch (IllegalArgumentException ex2) {
        LOGGER.warn("Unable to convert [{}] to a valid DateTime.", text);
      }
    }

    if (dt != null) {
      super.setValue(dt);
    }
  }
}
