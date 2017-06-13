package com.study.sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import com.study.sample.entity.User;
import com.study.sample.entity.UserType;
import com.study.sample.exception.SQLExecutionException;
import com.study.sample.manager.DatabaseConfigManager;

public class UserDAO extends AbstractDAO {
	public boolean add(String userName, String password){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.insertuser");
		sql = String.format(sql, userName, password);
		try {
			executeSQLQuery(sql);
			result = true;
		} catch (SQLExecutionException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return result;
	}
	
	public boolean remove(){
		boolean result = false;
		return result;		
	}
	
	public List<User> findAll(){
		List<User> result = null; 
		String sql = DatabaseConfigManager.getProperty("query.logic.getusers");
		try {
			ResultSet rs = executeSQLQuery(sql);
			List<User> innerList = new ArrayList<User>();
			User user;
			while (rs.next()){
				user = new User();
				user.setLogin(rs.getString(1));
				innerList.add(user);
			}
			result = innerList;
		} catch (SQLExecutionException | SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return result;
	}
	
	public boolean addSubscriptionForUser(){
		boolean result = false;
		return result;
	}
	
	public boolean changeSubscriptionForUser(){
		boolean result = false;
		return result;
	}
	
	public UserType checkUserPassword(String userName, String password){
		UserType result = null;
		String sql = DatabaseConfigManager.getProperty("query.logic.checkpassword");
		sql = String.format(sql, userName, password);
		try {
			ResultSet resultSet = executeSQLQuery(sql);
			if (resultSet.next()){
				result = UserType.valueOf(resultSet.getString(3));
			}
		} catch (SQLException | SQLExecutionException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return result;
	}
	
}
