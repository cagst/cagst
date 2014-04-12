package com.cagst.common.web.servlet.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;
import org.springframework.web.util.ExpressionEvaluationUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.TagUtils;

/**
 * This class is responsible for retrieving static resources (resources that do not need to be
 * internationalized) from a properties file.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class StaticResourceTag extends HtmlEscapingAwareTag {
	private static final long serialVersionUID = 6991954844642076704L;
	private static final Logger LOGGER = LoggerFactory.getLogger(StaticResourceTag.class);

	private String key;
	private String var;
	private String scope = TagUtils.SCOPE_PAGE;

	public void setKey(final String key) {
		this.key = key;
	}

	public void setVar(final String var) {
		this.var = var;
	}

	public void setScope(final String scope) {
		this.scope = scope;
	}

	public static String staticResource(final String key) {
		return StaticResourceAssistant.getString(key);
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		try {
			String location = staticResource(key);

			if (StringUtils.startsWith(location, "/") && !StringUtils.startsWith(location, "//")) {
				// Locally hosted static content in the app
				// needs to have the context path appended
				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				String contextPath = request.getContextPath();
				location = contextPath + location;
			}

			location = isHtmlEscape() ? HtmlUtils.htmlEscape(location) : location;

			// Expose as variable, if demanded, else write to the page.
			String resolvedVar = ExpressionEvaluationUtils.evaluateString("var", this.var, pageContext);
			if (!StringUtils.isEmpty(resolvedVar)) {
				String resolvedScope = ExpressionEvaluationUtils.evaluateString("scope", this.scope, pageContext);
				pageContext.setAttribute(resolvedVar, location, TagUtils.getScope(resolvedScope));
			} else {
				pageContext.getOut().write(location);
			}
		} catch (IOException ex) {
			LOGGER.error("Exception processing the static resource [" + key + "]", ex);
		}

		return EVAL_BODY_INCLUDE;
	}
}
