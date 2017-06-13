package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.logic.SubscriptionLogic;
import com.study.sample.manager.ConfigurationManager;

public class SubscriptionRemoveAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (SubscriptionLogic.removeSubscription(request)){
			page = ConfigurationManager.getProperty("path.page.subscription");
		}
		return page;
	}

}
