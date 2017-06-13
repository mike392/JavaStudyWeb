package com.study.sample.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Action {
	public static Logger logger = LogManager.getLogger(Action.class);
	public abstract String execute(HttpServletRequest request);
}
