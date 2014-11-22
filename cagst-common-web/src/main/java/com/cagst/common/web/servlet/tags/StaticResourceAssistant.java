package com.cagst.common.web.servlet.tags;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * <i>StaticResourceAssistant</i> is a utility for looking up static resources from
 * {@link ResourceBundle}s that do not need to be internationalized. The major difference between
 * using this API over the {@link ResourceBundle} is that this class doesn't throw
 * {@link MissingResourceException}s; rather it massages them into <code>null</code> values.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public final class StaticResourceAssistant {
  private static final String STATIC_CONTENT = "properties.static.static";

  /**
   * Retrieves the string associated with the key passed in.
   *
   * @param key
   *     The {@link String} key to the resource.
   *
   * @return The resource or <code>null</code> if the resource is not found.
   */
  public static String getString(final String key) {
    ResourceBundle rb = ResourceBundle.getBundle(STATIC_CONTENT, Locale.getDefault(),
        StaticResourceAssistant.class.getClassLoader());

    try {
      // get the resource from the bundle
      return rb.getString(key);
    } catch (MissingResourceException ex) {
      // massage into a null value
      return null;
    }
  }

  /**
   * Retrieves the string associated with the key passed in.
   *
   * @param key
   *     The {@link String} key to the resource.
   * @param request
   *     The {@link HttpServletRequest}
   *
   * @return The resource or <code>null</code> if the resource is not found.
   */
  public static String getString(final String key, final HttpServletRequest request) {
    String location = getString(key);

    if (StringUtils.startsWith(location, "/") && !StringUtils.startsWith(location, "//")) {
      // Locally hosted static content in the app
      // needs to have the context path appended
      String contextPath = request.getContextPath();
      location = contextPath + location;
    }

    return HtmlUtils.htmlEscape(location);
  }
}
