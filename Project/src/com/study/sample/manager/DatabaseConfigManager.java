package com.study.sample.manager;

import java.util.ResourceBundle;

public class DatabaseConfigManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.dbconfig");
	// ����� ��������� ���������� �� ����� config.properties
	private DatabaseConfigManager() { }
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
