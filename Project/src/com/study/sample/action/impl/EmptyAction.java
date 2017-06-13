package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.manager.ConfigurationManager;

public class EmptyAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		/* � ������ ������ ��� ������� ��������� � �����������
		* ������������� �� �������� ����� ������ */
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}
}
