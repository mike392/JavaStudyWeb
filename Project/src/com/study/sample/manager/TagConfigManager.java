package com.study.sample.manager;

import java.util.ResourceBundle;

public class TagConfigManager {
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.tagconfig");;
	// ����� ��������� ���������� �� ����� config.properties
	private TagConfigManager() { }
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
