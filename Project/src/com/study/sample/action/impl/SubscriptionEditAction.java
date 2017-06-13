package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.logic.SubscriptionLogic;
import com.study.sample.manager.ConfigurationManager;

public class SubscriptionEditAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (SubscriptionLogic.getSubscriptionToEdit(request)){
		page = ConfigurationManager.getProperty("path.page.subscriptionedit");
		}
		return page;
	}

}
