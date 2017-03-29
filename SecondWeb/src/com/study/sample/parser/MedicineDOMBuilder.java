package com.study.sample.parser;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.study.sample.entity.AttributeEnum;
import com.study.sample.entity.DomesticMedicine;
import com.study.sample.entity.ForeignMedicine;
import com.study.sample.entity.Medicine;

public class MedicineDOMBuilder extends AbstractMedicineBuilder {
	private DocumentBuilder docBuilder;
	private Set<Medicine> medicines;
	private Medicine current = null;
	private AttributeEnum currentEnum = null;
	
	public MedicineDOMBuilder(){
		medicines = new HashSet<Medicine>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("������ ������������ �������: " + e);
		}
	}
	
	public Set<Medicine> getMedicines(){
		return medicines;
	}

	public void buildSetMedicines(String fileName){
		Document doc = null;
		try {
		// parsing XML-��������� � �������� ����������� ���������
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
		// ��������� ������ �������� ��������� <student>
			NodeList medicineList = root.getChildNodes();
			for (int i = 0; i < medicineList.getLength(); i++) {
				if (medicineList.item(i).getNodeType() == Node.ELEMENT_NODE && medicineList.item(i).getNodeName().matches("\\S.*?medicine")){
					current = (medicineList.item(i).getNodeName().contains("foreign")) ? new ForeignMedicine() : new DomesticMedicine();
					Element medicineElement = (Element) medicineList.item(i);
					buildMedicine(medicineElement);
					medicines.add(current);
					current = null;
				}
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private void buildMedicine(Element medicineElement) {
		for (int i = 0; i< medicineElement.getAttributes().getLength(); i++){
			Node attr = medicineElement.getAttributes().item(i);
			AttributeEnum temp = getAttributeEnumByName(attr.getNodeName());
			if (temp != null){
				currentEnum = temp;
				invokeSetterForAttribute(attr.getNodeValue());
			}
		}
	}
	
	private AttributeEnum getAttributeEnumByName(String name) {
		AttributeEnum result = null;
		for (AttributeEnum item : AttributeEnum.values()){
			if (item.getValue().matches(name)){
				result = item;
				break;
			}
		}
		return result;
	}
	
	private void invokeSetterForAttribute(String s) {
		Method method = getSetterMethodForAttribute(current.getClass());
		try {
			Object param;
			if (method.getParameterTypes()[0].equals(LocalDate.class)){
				Locale locale = getLocaleByDatePattern(s);
				DateTimeFormatter frmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale);
				param = LocalDate.parse(s, frmt);
			} else {
				Constructor<?> paramConstructor = method.getParameterTypes()[0].getConstructor(String.class);
				param = paramConstructor.newInstance(s);
			}
			method.invoke(current, param);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	private Locale getLocaleByDatePattern(String s) {
		Locale result = null;
		for (Locale locale : Locale.getAvailableLocales()){
			try {
				DateTimeFormatter frmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale);
				LocalDate.parse(s, frmt);
				result = locale;
				break;
			} catch(DateTimeParseException e){
				//System.out.println("Wrong locale " + locale.toString());
			}
		}
		return result;
	}

	private Method getSetterMethodForAttribute(Class<?> clazz) {
		Method result = null;
		List<Field> fieldList = getAllFields(new ArrayList<Field>(), clazz);
		for (Field item : fieldList){
			if (item.getName().toUpperCase().matches(currentEnum.name())){
				result = getMethodByName(item, clazz);
				break;
			}
		}
		return result;
	}
	
	private Method getMethodByName(Field item, Class<?> clazz) {
		Method[] classMethods = clazz.getMethods();
		Method result = null;
		for (Method method : classMethods){
			if (method.getName().toUpperCase().matches("SET" + item.getName().toUpperCase())){
				result = method;
				break;
			}
		}
		return result;
	}

	private List<Field> getAllFields(List<Field> fields, Class<?> type) {
	    fields.addAll(Arrays.asList(type.getDeclaredFields()));
	    if (type.getSuperclass() != null) {
	        getAllFields(fields, type.getSuperclass());
	    }
	    return fields;
	}
}
