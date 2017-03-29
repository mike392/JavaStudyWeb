package com.study.sample.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadController
 */
@WebServlet({ "/UploadController", "/uploadFile" })
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET method");
		request.getRequestDispatcher("pages/upload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		if (filePart.getSubmittedFileName().contains(".xml")){
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();
		    byte[] buffer = new byte[fileContent.available()];
		    fileContent.read(buffer);
		    String path = Paths.get(request.getServletContext().getRealPath("\\"), request.getServletContext().getInitParameter("file-upload"), request.getServletContext().getInitParameter("file-upload-name")).toString();
		    File targetFile = new File(path);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    request.setAttribute("info", "File uploaded!");
		} else {
			request.setAttribute("err", "Please, upload XML file!");
		}
		request.getRequestDispatcher("pages/upload.jsp").forward(request, response);
	}

}
