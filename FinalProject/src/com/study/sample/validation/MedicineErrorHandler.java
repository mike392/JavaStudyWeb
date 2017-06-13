package com.study.sample.validation;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MedicineErrorHandler extends DefaultHandler {
	private Logger logger = LogManager.getLogger("com.study.parser.validation");
	public MedicineErrorHandler(String log) throws IOException {
	// ��������� ����� � ������� ������ ������
	}
	public void warning(SAXParseException e) {
	logger.warn(getLineAddress(e) + "-" + e.getMessage());
	}
	public void error(SAXParseException e) {
	logger.error(getLineAddress(e) + " - " + e.getMessage());
	}
	public void fatalError(SAXParseException e) {
	logger.fatal(getLineAddress(e) + " - " + e.getMessage());
	}
	private String getLineAddress(SAXParseException e) {
	// ����������� ������ � ������� ������
	return e.getLineNumber() + " : " + e.getColumnNumber();
	}
}
