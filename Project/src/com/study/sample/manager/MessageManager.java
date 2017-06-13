package com.study.sample.manager;

import java.util.ResourceBundle;

public class MessageManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.message");
	// ����� ��������� ���������� �� ����� messages.properties
	private MessageManager() { }
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
