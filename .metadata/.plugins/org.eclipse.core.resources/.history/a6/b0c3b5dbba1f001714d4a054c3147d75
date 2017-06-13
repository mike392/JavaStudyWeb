package com.study.sample.util;

import java.util.ResourceBundle;

public class DatabaseConfigManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.dbconfig");
	// класс извлекает информацию из файла config.properties
	private DatabaseConfigManager() { }
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
