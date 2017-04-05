package com.study.sample.logic;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.util.TimeCalculationException;

public class TimeLogic extends Logic{

	public static void calculateTimePeriod(HttpServletRequest request) throws TimeCalculationException {
		GregorianCalendar gc = new GregorianCalendar();
		String timeJsp = request.getParameter("time");
		float delta = ((float)(gc.getTimeInMillis() - Long.parseLong(timeJsp)))/1_000;
		request.setAttribute("res", delta);
		
	}
}
