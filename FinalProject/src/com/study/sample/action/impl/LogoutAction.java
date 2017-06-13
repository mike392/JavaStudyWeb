package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.util.ConfigurationManager;

public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		// уничтожение сессии
		request.getSession().invalidate();
		return page;
	}

}
