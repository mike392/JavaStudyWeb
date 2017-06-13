package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.logic.UserSubscriptionLogic;
import com.study.sample.manager.ConfigurationManager;

public class BlockUserSubscriptionAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (UserSubscriptionLogic.blockUserSubscription(request)){
			page = ConfigurationManager.getProperty("path.page.usermanagement");
		}
		return page;
	}

}
