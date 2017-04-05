package com.study.sample.action;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.impl.EmptyAction;
import com.study.sample.util.MessageManager;

public class ActionFactory {
	public Action defineAction(HttpServletRequest request) {
		Action current = new EmptyAction();
		// извлечение имени команды из запроса
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
		// если команда не задана в текущем запросе
			return current;
		}
		// получение объекта, соответствующего команде
		try {
			ActionEnum currentEnum = ActionEnum.valueOf(action.toUpperCase());
			current = currentEnum.getAction();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
