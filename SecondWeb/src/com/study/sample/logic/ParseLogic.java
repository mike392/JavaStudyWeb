package com.study.sample.logic;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.study.sample.entity.Medicine;
import com.study.sample.parser.AbstractMedicineBuilder;
import com.study.sample.parser.MedicineBuilderFactory;
import com.study.sample.util.ParsingException;
import com.study.sample.validation.SAXValidator;

public class ParseLogic extends Logic{

	public static void parse(HttpServletRequest request) throws ParsingException{
		String path = Paths.get(request.getServletContext().getRealPath("\\"), request.getServletContext().getInitParameter("file-upload")).toString();
		String err = "";
		if (SAXValidator.isValidXML(path)){
			AbstractMedicineBuilder builder = new MedicineBuilderFactory().createMedicineBuilder(request.getParameter("parser"));
			LocalTime time = LocalTime.now();
			builder.buildSetMedicines(Paths.get(path, "Pharmacy.xml").toString());
			Set<Medicine> medicines = builder.getMedicines();
			long diff = Duration.between(time, LocalTime.now()).toNanos()/1_000_000;
			request.setAttribute("res", medicines);
			request.setAttribute("diff", diff);
		} else {
			err = "Validation failed";
			request.setAttribute("err", err);
		}
	}
}