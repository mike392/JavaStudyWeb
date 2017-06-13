package com.study.sample.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.study.sample.manager.DatabaseConfigManager;

public class ConnectionPool <T extends Connection> {
	static Logger logger = LogManager.getLogger(ConnectionPool.class);
	private static AtomicBoolean isInitialized = new AtomicBoolean(false);
	private static ConnectionPool<OracleConnection> instance;
	private BlockingQueue<Connection> freePool;
	private List<Connection> usedPool = new CopyOnWriteArrayList<Connection>();
	private static final ReentrantLock lock = new ReentrantLock();
	
	private ConnectionPool(Class<T> clazz){
		int poolSize = Integer.parseInt(DatabaseConfigManager.getProperty("connectionpool.size"));
		freePool = new ArrayBlockingQueue<Connection>(poolSize);
		for (int i=0;i<poolSize;i++){
			T conn;
			try {
				conn = clazz.newInstance();
				freePool.offer(conn);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.log(Level.ERROR, "Cannot create Oracle connection " + e.getLocalizedMessage());
			}
			
		}
	}
	public static ConnectionPool<?> getInstance(){
		if (!isInitialized.get()){
			try {
				lock.lock();
				instance = new ConnectionPool<OracleConnection>(OracleConnection.class);
				isInitialized.set(true);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = freePool.take();
			usedPool.add(connection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "Failed to take connection from the pool" + e.getLocalizedMessage());
		}
		return connection;
	}
	
	public <T extends Connection> void releaseConnection(T cn){
		freePool.offer(cn);
		usedPool.remove(cn);
	}
	
	public void closeConnections() {
		while (!freePool.isEmpty()){
			Connection conn;
			try {
				conn = freePool.take();
				conn.close();
			} catch (InterruptedException | SQLException e) {
				logger.log(Level.ERROR, "Failed to take connection from the pool" + e.getLocalizedMessage());
			}
		}
		while (!usedPool.isEmpty()){
			Connection conn;
			try {
				conn = usedPool.get(usedPool.size() - 1);
				conn.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Failed to take connection from the pool" + e.getLocalizedMessage());
			}
		}
		
	}
}
