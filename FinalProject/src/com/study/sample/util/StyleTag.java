package com.study.sample.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
@SuppressWarnings("serial")
public class StyleTag extends TagSupport {
	@Override
	public int doStartTag() throws JspException {
		
		String body = TagConfigManager.getProperty("additional.style");
		try {
			JspWriter out = pageContext.getOut();
			out.write(body);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
		}
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}