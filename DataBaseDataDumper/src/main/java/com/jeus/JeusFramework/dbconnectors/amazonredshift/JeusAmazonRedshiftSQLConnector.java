package com.jeus.JeusFramework.dbconnectors.amazonredshift;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusAmazonRedshiftSQLConnector extends JeusJDBCConnector {

	public JeusAmazonRedshiftSQLConnector(String ip, int portNo, String schema, String userName, String password)
			throws SQLException {
		super.connect("jdbc:mysql://" + ip + ":" + portNo + "/" + schema, userName, password);
	}
}
