package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.manager.ConfigurationManager;

public class ChangeLocaleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String role = request.getSession().getAttribute("role").toString();
		if (role.matches("ADMIN")){
			page = ConfigurationManager.getProperty("path.page.main");
		} else {
			page = ConfigurationManager.getProperty("path.page.usermain");
		}
		request.getSession().setAttribute("locale", request.getParameter("locale"));
		return page;
	}

}
