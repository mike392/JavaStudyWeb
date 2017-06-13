package com.study.sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import com.study.sample.entity.UserSubscription;
import com.study.sample.exception.SQLExecutionException;
import com.study.sample.manager.DatabaseConfigManager;

public class UserSubscriptionDAO extends AbstractDAO {
	public boolean add(String userName, String subscriptionName){
		boolean result = true;
		String sql = DatabaseConfigManager.getProperty("query.logic.addusersubscription");
		sql = String.format(sql, userName, subscriptionName);
		try {
			executeSQLQuery(sql);
		} catch (SQLExecutionException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return result;
	}
	
	public List<UserSubscription> findAll(){
		List<UserSubscription> userSubList = null;
		String sql = DatabaseConfigManager.getProperty("query.logic.getusersubscriptions");
		try {
			ResultSet rs = executeSQLQuery(sql);
			List<UserSubscription> innerList = new ArrayList<UserSubscription>();
			UserSubscription userSub;
			while (rs.next()){
				userSub = new UserSubscription();
				userSub.setUsername(rs.getString(1));
				userSub.setSubname(rs.getString(2));
				userSub.setBalance(rs.getDouble(3));
				userSub.setBlocked(rs.getString(4));
				innerList.add(userSub);
			}
			userSubList = innerList;
		} catch (SQLExecutionException | SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return userSubList;
}
	public boolean remove(String userName, String subscriptionName){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.removeusersubscription");
		sql = String.format(sql, userName, subscriptionName);
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
	
	public boolean removeForGivenSubscription(String subscriptionName){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.removeusersubscriptionbysubname");
		sql = String.format(sql, subscriptionName);
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
	
	public boolean update(String subscriptionName, String userName){
		boolean result = true;
		String sql = DatabaseConfigManager.getProperty("query.logic.updateusersubscription");
		sql = String.format(sql, subscriptionName, userName);
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
	
	public boolean block(String userName, String subscriptionName){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.blockusersubscription");
		sql = String.format(sql, userName, subscriptionName);
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
	
	public boolean unblock(String userName, String subscriptionName){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.unblockusersubscription");
		sql = String.format(sql, userName, subscriptionName);
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

	public UserSubscription findSubscription(String userName) {
		UserSubscription result = null;
		String sql = DatabaseConfigManager.getProperty("query.logic.getusersubscription");
		sql = String.format(sql, userName);
		try {
			ResultSet rs = executeSQLQuery(sql);
			UserSubscription userSub;
			while (rs.next()){
				userSub = new UserSubscription();
				userSub.setUsername(rs.getString(1));
				userSub.setSubname(rs.getString(2));
				userSub.setBalance(rs.getDouble(3));
				userSub.setBlocked(rs.getString(4));
				result = userSub;
			}
		} catch (SQLExecutionException | SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return result;
	}

	public boolean pay(String balance, String userName) {
		boolean result = true;
		String sql = DatabaseConfigManager.getProperty("query.logic.payusersubscription");
		sql = String.format(sql, balance, userName);
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
	
	
}
