package com.cagst.common.propertyeditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link PropertyEditor} that will retrieve the {@link Date} represented by the specified string.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class DateEditor extends PropertyEditorSupport {
  private static final Logger LOGGER = LoggerFactory.getLogger(DateEditor.class);

  private final SimpleDateFormat formatForWeb = new SimpleDateFormat("yyyy-MM-dd");
  private final SimpleDateFormat alternateFormat = new SimpleDateFormat("MM/dd/yyyy");

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

    if (getValue() instanceof Date) {
      Date dt = (Date) getValue();
      return formatForWeb.format(dt);
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
    Date dt = null;
    try {
      dt = formatForWeb.parse(text);
    } catch (ParseException ex) {
      // try the alternate format
      try {
        dt = alternateFormat.parse(text);
      } catch (ParseException ex2) {
        LOGGER.warn("Unable to convert [{}] to a valid Date.", text);
      }
    }

    if (dt != null) {
      super.setValue(dt);
    }
  }
}
