package com.jeus.JeusFramework.dbconnectors.mysql;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusMYSQLConnector extends JeusJDBCConnector {

	public JeusMYSQLConnector(String ip, int portNo, String schema, String userName, String password)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		super.connect("jdbc:mysql://" + ip + ":" + portNo + "/" + schema+"?zeroDateTimeBehavior=convertToNull", userName, password);
	}
}
