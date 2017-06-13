package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.action.Action;
import com.study.sample.logic.UserSubscriptionLogic;
import com.study.sample.manager.ConfigurationManager;

public class UserUpdateSubscriptionAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String userName = request.getParameter("username");
		if (UserSubscriptionLogic.updateUserSubscription(userName, request)){
			page = ConfigurationManager.getProperty("path.page.usermanagementedit");
		}
		return page;
	}

}
