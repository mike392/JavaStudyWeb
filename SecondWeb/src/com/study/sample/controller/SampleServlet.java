package com.study.sample.controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/timeaction")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GregorianCalendar gc = new GregorianCalendar();
		String timeJsp = request.getParameter("time");
		float delta = ((float)(gc.getTimeInMillis() - Long.parseLong(timeJsp)))/1_000;
		request.setAttribute("res", delta);
		request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GregorianCalendar gc = new GregorianCalendar();
		String timeJsp = request.getParameter("time");
		float delta = ((float)(gc.getTimeInMillis() - Long.parseLong(timeJsp)))/1_000;
		request.setAttribute("res", delta);
		request.getRequestDispatcher("pages/result.jsp").forward(request, response);
	}

}
