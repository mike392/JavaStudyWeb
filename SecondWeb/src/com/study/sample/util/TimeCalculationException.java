package com.study.sample.util;

public class TimeCalculationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TimeCalculationException(){} 
	
	public TimeCalculationException(String message, Throwable e){
		super(message, e);
	}
	
	public TimeCalculationException(String message){
		super(message);
	}
	
	public TimeCalculationException(Throwable e){
		super(e);
	}
}
