package com.study.sample.parser;

import java.io.IOException;
import java.util.Set;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import com.study.sample.entity.Medicine;

public class MedicineSAXBuilder extends AbstractMedicineBuilder {
	private Set<Medicine> medicines;
	private MedicineHandler mh;
	private XMLReader reader;

		public MedicineSAXBuilder() {
		// �������� SAX-�����������
			mh = new MedicineHandler();
			try {
		// �������� �������-�����������
				reader = XMLReaderFactory.createXMLReader();
				reader.setContentHandler(mh);
			} catch (SAXException e) {
				System.err.print("������ SAX �������: " + e);
			}
		}
		
		public Set<Medicine> getMedicines() {
			return medicines;
		}
		
		public void buildSetMedicines(String fileName) {
			try {
			// ������ XML-���������
				reader.parse(fileName);
			} catch (SAXException e) {
				System.err.print("������ SAX �������: " + e);
			} catch (IOException e) {
				System.err.print("������ I/� ������: " + e);
			}
			medicines = mh.getMedicines();
		}
	}

