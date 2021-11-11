package com.jeus.JeusFramework.dbconnectors.oracledb;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusOracleDbConnector extends JeusJDBCConnector {

	public JeusOracleDbConnector(String ip, int portNo, String serviceName, String userName, String password)
			throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		super.connect("jdbc:oracle:thin:@" + ip + ":" + portNo + ":" + serviceName, userName, password);
	}
}
