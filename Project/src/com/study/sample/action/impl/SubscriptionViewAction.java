package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.logic.UserSubscriptionLogic;
import com.study.sample.manager.ConfigurationManager;

public class SubscriptionViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (UserSubscriptionLogic.getSubscriptionForUser(request)){
			page = ConfigurationManager.getProperty("path.page.subscriptionview");
		}
		return page;
	}

}
