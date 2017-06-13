package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.logic.SignupLogic;
import com.study.sample.manager.ConfigurationManager;

public class SignupAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (SignupLogic.successfullSignup(request)){
			page = ConfigurationManager.getProperty("path.page.signup");
		}
		return page;
	}

}
