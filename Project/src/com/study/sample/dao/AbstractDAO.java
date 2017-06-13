package com.study.sample.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.study.sample.exception.SQLExecutionException;
import com.study.sample.pool.ConnectionPool;

public class AbstractDAO {
	protected static Logger logger = LogManager.getLogger(AbstractDAO.class);
	private Connection conn;
	private Statement st;
	
	protected ResultSet executeSQLQuery(String sql) throws SQLExecutionException{
		ResultSet result = null;
		conn = ConnectionPool.getInstance().getConnection();
		try {
			st = conn.createStatement();
			result = st.executeQuery(sql);
		} catch (SQLException e) {
			
			throw new SQLExecutionException(e);
		} 
		return result;
	}
	
	protected void closeStatement(){
		// null
		try {
			st.close();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		}
	}
	
	protected void closeConnection(){
		ConnectionPool.getInstance().releaseConnection(conn);
	}
}
