package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.action.Action;
import com.study.sample.logic.UserSubscriptionLogic;
import com.study.sample.manager.ConfigurationManager;

public class UserManagementAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (UserSubscriptionLogic.getUserSubscriptions(request)){
			page = ConfigurationManager.getProperty("path.page.usermanagement");
		}
		return page;
	}

}
