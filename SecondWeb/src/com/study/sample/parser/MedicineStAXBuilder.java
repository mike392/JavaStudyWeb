package com.study.sample.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.study.sample.entity.AttributeEnum;
import com.study.sample.entity.DomesticMedicine;
import com.study.sample.entity.ForeignMedicine;
import com.study.sample.entity.Medicine;

public class MedicineStAXBuilder extends AbstractMedicineBuilder {
	private HashSet<Medicine> medicines = new HashSet<>();
	Medicine current = null;
	AttributeEnum currentEnum = null;
	private XMLInputFactory inputFactory;
	
	public MedicineStAXBuilder(){
		inputFactory = XMLInputFactory.newInstance();
	}
	
	public Set<Medicine> getMedicines() {
		return medicines;
		}
	
	public void buildSetMedicines(String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (name.matches("\\S.*?medicine")) {
						buildMedicine(reader);
						medicines.add(current);
						current = null;
					}
				}
			}
		} catch (XMLStreamException ex) {
			System.err.println("StAX parsing error! " + ex.getMessage());
		} catch (FileNotFoundException ex) {
			System.err.println("File " + fileName + " not found! " + ex);
		} finally {
			try {
			if (inputStream != null) {
				inputStream.close();
			}
			} catch (IOException e) {
				System.err.println("Impossible close file "+fileName+" : "+e);
			}
		}
	}

	private Medicine buildMedicine(XMLStreamReader reader) {
		current = reader.getLocalName().contains("foreign") ? new ForeignMedicine() : new DomesticMedicine(); 
		for (int i = 0; i < reader.getAttributeCount(); i++){
			AttributeEnum temp = getAttributeEnumByName(reader.getAttributeLocalName(i));
			if (temp != null){
				currentEnum = temp;
				invokeSetterForAttribute(reader.getAttributeValue(i));
			}
		}
		return current;
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