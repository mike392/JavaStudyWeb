package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;

import com.study.sample.action.Action;
import com.study.sample.logic.ParseLogic;
import com.study.sample.util.ConfigurationManager;
import com.study.sample.util.ParsingException;

public class ParseAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			ParseLogic.parse(request);
			page = ConfigurationManager.getProperty("path.page.parse");
		} catch (ParsingException e){
			logger.log(Level.ERROR, "Failed to parse file - " + e.getMessage());
		}
		return page;
	}

}