package com.study.sample.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.sample.action.Action;
import com.study.sample.action.ActionFactory;
import com.study.sample.manager.ConfigurationManager;
import com.study.sample.manager.MessageManager;
import com.study.sample.pool.ConnectionPool;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String page = null;
			// ����������� �������, ��������� �� JSP
			ActionFactory client = new ActionFactory();
			Action command = client.defineAction(request);
			/*
			* ����� �������������� ������ execute() � �������� ����������
			* ������-����������� ���������� �������
			*/
			page = command.execute(request);
			// ����� ���������� �������� ������
			// page = null; // ��������������������!
			if (page != null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			// ����� �������� ������ �� ������
				dispatcher.forward(request, response);
			} else {
			// ��������� �������� c c��������� �� ������
				page = ConfigurationManager.getProperty("path.page.index");
				request.getSession().setAttribute("nullPage",
						MessageManager.getProperty("message.nullpage"));
				response.sendRedirect(request.getContextPath() + page);
			}
		}
	public void destroy(){
		ConnectionPool.getInstance().closeConnections();
		super.destroy();
	}

}
