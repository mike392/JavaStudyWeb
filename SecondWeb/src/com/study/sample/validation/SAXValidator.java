package com.study.sample.validation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
public class SAXValidator {
	public static boolean isValidXML(String path) {
		boolean result = false;
		String filename = Paths.get(path, "\\data\\Pharmacy.xml").toString();
		String schemaname = Paths.get(path, "\\data\\Pharmacy.xsd").toString();
		String logname = Paths.get(path, "\\logs\\log.txt").toString();
		Schema schema = null;
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		try {
		// ��������� �������� � �������������� XSD
			schema = factory.newSchema(new File(schemaname));
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
		// �������� �������-�������
			SAXParser parser = spf.newSAXParser();
		// ��������� ����������� ������ � ������
			parser.parse(filename, new MedicineErrorHandler(logname));
			System.out.println(filename + " is valid");
			result = true;
		} catch (ParserConfigurationException e) {
			System.err.println(filename + " config error: " + e.getMessage());
		} catch (SAXException e) {
			System.err.println(filename + " SAX error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
		return result;
	}
}