package test.com.study.sample.main;

import org.junit.Before;
import org.junit.Test;

import com.study.sample.parser.MedicineSAXBuilder;

public class ParserTest {

	@Before
	public void initTest(){
		MedicineSAXBuilder saxBuilder = new MedicineSAXBuilder();
		saxBuilder.buildSetMedicines("data/Pharmacy.xml");
		System.out.println(saxBuilder.getMedicines());
	}
	
	@Test
	public void testMain(){}
}
