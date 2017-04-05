package test.com.study.sample.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.study.sample.dao.ConnectionManager;


public class ConnectionManagerTest {
	
	@Test
	public void getConnectionTest(){
		Connection cn = ConnectionManager.getInstance().getConnection();
		try {
			Statement st = cn.createStatement();
			ResultSet set = st.executeQuery("SELECT COUNT(*) as cnt FROM ALL_TABLES");
			while (set.next()){
				System.out.println(String.valueOf(set.getInt("cnt")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
