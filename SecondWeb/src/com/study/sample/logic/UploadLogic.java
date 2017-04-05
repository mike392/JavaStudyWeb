package com.study.sample.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.logging.log4j.Level;

public class UploadLogic extends Logic{

	public static boolean upload(HttpServletRequest request) {
		boolean result = false;
		Part filePart;
		try {
			filePart = request.getPart("file");
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
			result = true;
		} catch (IOException | ServletException e) {
			logger.log(Level.ERROR, "Failed to upload file - " + e.getLocalizedMessage());
		} 
		return result;
	}

}
