package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.action.Action;
import com.study.sample.logic.UploadLogic;
import com.study.sample.util.ConfigurationManager;

public class UploadAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		if (UploadLogic.upload(request)){
			page = ConfigurationManager.getProperty("path.page.upload");
		}
		return page;
	}

}
