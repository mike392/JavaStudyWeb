package com.study.sample.action;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.impl.EmptyAction;
import com.study.sample.manager.MessageManager;

public class ActionFactory {
	public Action defineAction(HttpServletRequest request) {
		Action current = new EmptyAction();
		// ���������� ����� ������� �� �������
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
		// ���� ������� �� ������ � ������� �������
			return current;
		}
		// ��������� �������, ���������������� �������
		try {
			ActionEnum currentEnum = ActionEnum.valueOf(action.toUpperCase());
			current = currentEnum.getAction();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
