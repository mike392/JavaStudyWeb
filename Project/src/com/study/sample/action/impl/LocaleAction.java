package com.study.sample.action.impl;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import com.study.sample.action.Action;
import com.study.sample.manager.ConfigurationManager;

public class LocaleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.locale");
		Date date = new Date();
		request.setAttribute("now", date);
		return page;
	}

}
