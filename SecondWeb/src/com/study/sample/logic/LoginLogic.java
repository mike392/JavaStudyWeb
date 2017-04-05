package com.study.sample.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.Level;
import com.study.sample.dao.ConnectionManager;
import com.study.sample.util.DatabaseConfigManager;
import com.study.sample.util.StringHashProvider;

public class LoginLogic extends Logic{

	public static boolean checkLogin(String login, String pass) {
		boolean result = false;	
		Connection cn = ConnectionManager.getInstance().getConnection();
		try {
			Statement st = cn.createStatement();
			String query = DatabaseConfigManager.getProperty("query.logic.checkpassword");
			String passHash = StringHashProvider.getHashValue(pass);
			ResultSet resultSet = st.executeQuery(String.format(query, login, passHash));
			if (resultSet.next()){
				result = true;
			} 
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			ConnectionManager.getInstance().releaseConnection(cn);
		} 
		return result;

		}
	}

