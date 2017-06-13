package com.study.sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import com.study.sample.entity.Subscription;
import com.study.sample.exception.SQLExecutionException;
import com.study.sample.manager.DatabaseConfigManager;


public class SubscriptionDAO extends AbstractDAO {
	public boolean add(Subscription sub){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.addsubscription");
		sql = String.format(sql, sub.getName(), sub.getPrice());
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
	
	public boolean update(Subscription sub, String prevName){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.updatesubscription");
		sql = String.format(sql, sub.getPrice(), sub.getName(), prevName);
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
	
	public boolean remove(String subscriptionName){
		boolean result = false;
		String sql = DatabaseConfigManager.getProperty("query.logic.removesubscription");
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

	public List<Subscription> findAll() {
		List<Subscription> result = new ArrayList<Subscription>();
		String sql = DatabaseConfigManager.getProperty("query.logic.getsubscriptions");
		try {
			ResultSet rs = executeSQLQuery(sql);
			Subscription sub;
			while (rs.next()){
				sub = new Subscription();
				sub.setName(rs.getString(1));
				sub.setPrice(rs.getDouble(2));
				result.add(sub);
			}
		} catch (SQLExecutionException | SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}
		return result;	
	}
	
	public Subscription findSubscription(String name){
		Subscription result = null;
		String sql = DatabaseConfigManager.getProperty("query.logic.editsubscription");
		sql = String.format(sql, name);
		try {
			ResultSet rs = executeSQLQuery(sql);
			if(rs.next()){
				Subscription sub = new Subscription();
				sub.setName(rs.getString(1));
				sub.setPrice(rs.getDouble(2));
				result = sub;
			}
		} catch (SQLExecutionException | SQLException e) {
			logger.log(Level.ERROR, "SQL error occurred - " + e.getLocalizedMessage());
		} finally {
			closeStatement();
			closeConnection();
		}		
		return result;
	}
}
