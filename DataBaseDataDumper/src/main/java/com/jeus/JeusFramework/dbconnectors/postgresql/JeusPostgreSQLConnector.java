package com.jeus.JeusFramework.dbconnectors.postgresql;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusPostgreSQLConnector extends JeusJDBCConnector {

	public JeusPostgreSQLConnector(String ip, int portNo, String schema, String userName, String password)
			throws SQLException {
		try {
			 Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		super.connect("jdbc:postgresql://" + ip + ":" + portNo + "/" + schema, userName, password);
	}
}
