package com.study.sample.dao;

import java.sql.Connection;
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
import com.study.sample.util.DatabaseConfigManager;

public class ConnectionManager <T extends Connection> {
	private static Logger logger = LogManager.getLogger(ConnectionManager.class);
	private static AtomicBoolean isInitialized = new AtomicBoolean(false);
	private static ConnectionManager<OracleConnection> instance;
	private BlockingQueue<Connection> freePool;
	private List<Connection> usedPool = new CopyOnWriteArrayList<Connection>();
	private static final ReentrantLock lock = new ReentrantLock();
	
	private ConnectionManager(Class<T> clazz){
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
	public static ConnectionManager<?> getInstance(){
		if (!isInitialized.get()){
			try {
				lock.lock();
				instance = new ConnectionManager<OracleConnection>(OracleConnection.class);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		Connection connection = null;
		while (connection == null) {
			if (hasFreeConnections()){
					try {
						connection = freePool.take();
						usedPool.add(connection);
					} catch (InterruptedException e) {
						logger.log(Level.ERROR, "Failed to take connection from the pool" + e.getLocalizedMessage());
					}
			} else {
				int sleepTime = Integer.parseInt(DatabaseConfigManager.getProperty("connectionpool.size"));
				try {
					TimeUnit.MILLISECONDS.sleep(sleepTime);
				} catch (InterruptedException e) {
					logger.log(Level.ERROR, "Interrupted exception " + e.getLocalizedMessage());
				}
				}
			}
		return connection;
	}
	
	public <T extends Connection> void releaseConnection(T cn){
		freePool.offer(cn);
		usedPool.remove(cn);
	}
	
	private boolean hasFreeConnections() {
		boolean result = false;
		if (!freePool.isEmpty()){
			result = true;
		}
		return result;
	}
}
