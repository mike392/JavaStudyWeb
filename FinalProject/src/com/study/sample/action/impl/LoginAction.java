package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.action.Action;
import com.study.sample.logic.LoginLogic;
import com.study.sample.util.ConfigurationManager;
import com.study.sample.util.MessageManager;

public class LoginAction implements Action {
	private static final String PARAM_NAME_LOGIN = "username";
	private static final String PARAM_NAME_PASSWORD = "password";
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		// ���������� �� ������� ������ � ������
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		// �������� ������ � ������
		if (LoginLogic.checkLogin(login, pass)) {
			request.setAttribute("user", login);
		// ����������� ���� � main.jsp
			page = ConfigurationManager.getProperty("path.page.main");
		} else {
			request.setAttribute("errorLoginPassMessage",
			MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}

}