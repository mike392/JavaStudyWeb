package com.study.sample.action.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.util.ConfigurationManager;

public class ChangeLocaleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.main");
		//request.getSession().setAttribute("locale", request.getParameter("locale"));
		Locale.setDefault(Locale.forLanguageTag("ru"));
		return page;
	}

}
