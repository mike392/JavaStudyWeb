package com.study.sample.tag;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.study.sample.manager.TagConfigManager;
@SuppressWarnings("serial")
public class FooterTag extends TagSupport {
	@Override
	public int doStartTag() throws JspException {
		
		String body = TagConfigManager.getProperty("tag.body");
		try {
			JspWriter out = pageContext.getOut();
			out.write(String.format(body, LocalDate.now().getYear()));
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
