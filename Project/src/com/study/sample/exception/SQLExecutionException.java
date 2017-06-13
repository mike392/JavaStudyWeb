package com.study.sample.exception;

public class SQLExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SQLExecutionException(){} 
	
	public SQLExecutionException(String message, Throwable e){
		super(message, e);
	}
	
	public SQLExecutionException(String message){
		super(message);
	}
	
	public SQLExecutionException(Throwable e){
		super(e);
	}

}
