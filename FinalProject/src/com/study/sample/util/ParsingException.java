package com.study.sample.util;

public class ParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParsingException(){} 
	
	public ParsingException(String message, Throwable e){
		super(message, e);
	}
	
	public ParsingException(String message){
		super(message);
	}
	
	public ParsingException(Throwable e){
		super(e);
	}

}
