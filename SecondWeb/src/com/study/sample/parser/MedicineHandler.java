package com.study.sample.parser;

import org.xml.sax.helpers.DefaultHandler;

import com.study.sample.entity.AttributeEnum;
import com.study.sample.entity.DomesticMedicine;
import com.study.sample.entity.ForeignMedicine;
import com.study.sample.entity.Medicine;
import java.util.List;
import java.util.Locale;
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
import java.util.Set;
import org.xml.sax.Attributes;


public class MedicineHandler extends DefaultHandler {
	private Set<Medicine> medicines;
	private Medicine current = null;
	private AttributeEnum currentEnum = null;

	public MedicineHandler() {
		medicines = new HashSet<Medicine>();
	}
	
	public Set<Medicine> getMedicines() {
		return medicines;
	}
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		if ("foreign-medicine".equals(localName) || "domestic-medicine".equals(localName)) {
			current = "foreign-medicine".equals(localName) ? new ForeignMedicine() : new DomesticMedicine();
		} 
		for (int i = 0; i< attrs.getLength(); i++){
			AttributeEnum temp = getAttributeEnumByName(attrs.getLocalName(i));
			if (temp != null){
				currentEnum = temp;
				invokeSetterForAttribute(attrs.getValue(i));
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

	public void endElement(String uri, String localName, String qName) {
		if ("foreign-medicine".equals(localName) || "domestic-medicine".equals(localName)) {
			medicines.add(current);
			current = null;
		}
		
	}
	public void characters(char[] ch, int start, int length) {
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

