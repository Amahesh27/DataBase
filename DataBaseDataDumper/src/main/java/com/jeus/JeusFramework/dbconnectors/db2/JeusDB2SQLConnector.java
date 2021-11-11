package com.jeus.JeusFramework.dbconnectors.db2;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusDB2SQLConnector extends JeusJDBCConnector {

	public JeusDB2SQLConnector(String ip, int portNo, String schema, String userName, String password)
			throws SQLException {
		super.connect("jdbc:mysql://" + ip + ":" + portNo + "/" + schema, userName, password);
	}
}
