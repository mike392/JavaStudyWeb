package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.util.ConfigurationManager;

public class EmptyAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		/* в случае ошибки или прямого обращения к контроллеру
		* переадресация на страницу ввода логина */
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}
}
