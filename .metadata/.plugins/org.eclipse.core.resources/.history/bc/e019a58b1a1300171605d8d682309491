package com.study.sample.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.sample.entity.Medicine;
import com.study.sample.parser.MedicineSAXBuilder;
import com.study.sample.validation.SAXValidator;

/**
 * Servlet implementation class XMLController
 */
@WebServlet("/parsed")
public class XMLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("\\");
		String err = "";
		if (SAXValidator.isValidXML(path)){
			MedicineSAXBuilder builder = new MedicineSAXBuilder();
			builder.buildSetMedicines(Paths.get(path, "\\data\\Pharmacy.xml").toString());
			List<Medicine> medicines = new ArrayList<Medicine>(builder.getMedicines());
			request.setAttribute("res", medicines);
		} else {
			err = "Validation failed";
		}
		request.setAttribute("err", err);		
		request.getRequestDispatcher("pages/parsingResult.jsp").forward(request, response);
	}

}
