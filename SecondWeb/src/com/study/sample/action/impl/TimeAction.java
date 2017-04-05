package com.study.sample.action.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;

import com.study.sample.action.Action;
import com.study.sample.logic.TimeLogic;
import com.study.sample.util.ConfigurationManager;
import com.study.sample.util.TimeCalculationException;

public class TimeAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			TimeLogic.calculateTimePeriod(request);
			page = ConfigurationManager.getProperty("path.page.time");
		} catch (TimeCalculationException e){
			logger.log(Level.ERROR, "Error when calculating time - " + e.getMessage());
		}
		return page;
	}

}
