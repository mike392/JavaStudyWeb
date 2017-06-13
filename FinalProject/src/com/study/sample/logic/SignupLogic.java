package com.study.sample.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;

import com.study.sample.dao.ConnectionManager;
import com.study.sample.util.DatabaseConfigManager;
import com.study.sample.util.StringHashProvider;

public class SignupLogic extends Logic {

	public static boolean successfullSignup(HttpServletRequest request) {
		boolean result = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String retypedPassword = request.getParameter("re-typed password");		
		if (password.equals(retypedPassword)){
			Connection conn = ConnectionManager.getInstance().getConnection();
			try {
				Statement st = conn.createStatement();
				String query = DatabaseConfigManager.getProperty("query.logic.insertuser");
				String passwordHash = StringHashProvider.getHashValue(password);
				st.executeQuery(String.format(query, username, passwordHash));
				result = true;
				
			} catch (SQLException e) {
				logger.log(Level.ERROR, " Failed to create statement for connection");
			} finally {
				ConnectionManager.getInstance().releaseConnection(conn);
			}			
		}
		return result;
	}

}
