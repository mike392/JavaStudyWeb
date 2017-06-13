package com.study.sample.util;

import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputValidator {
private static final Logger logger = LogManager.getLogger(InputValidator.class.getSimpleName());	
	public static boolean isAlphaString(Object value) {
		boolean result = true;
		String stringValue = (String) value;
		for (int i = 0; i < stringValue.length(); i++){
			char с = stringValue.charAt(i);
			if (!Character.isLetter(с)){
				result = false;
			}
		}
		if (String.valueOf(value).length() < 8 || String.valueOf(value).length() > 20){
			result = false;
		} 
		return result;
	}
	
	public static boolean isPositiveDouble(Object value) {
		boolean result = false;
		try {
			double parsedValue = Double.parseDouble((String) value);
			if (parsedValue >= 0){
				result = true;
			}
		} catch(NumberFormatException e){
			logger.log(Level.ERROR, "Wrong number was supplied");
		}
		return result; 
	}

	public static boolean isValidUsername(String username) {
		boolean result = false;
		Pattern pat = Pattern.compile("[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}");
		if (pat.matcher(username).find()){
			result = true;
		}
		return result;
	}

	public static boolean isValidPassword(String password) {
		boolean result = false;
		Pattern pat = Pattern.compile("^(?=.*\\d).{4,8}$");
		if (pat.matcher(password).find()){
			result = true;
		}
		return result;
	}

	public static boolean isValidPasswordConfirm(String password, String retypedPassword) {
		boolean result = false;
		if (password.equals(retypedPassword)){
			result = true;
		}
		return result;
	}
}
