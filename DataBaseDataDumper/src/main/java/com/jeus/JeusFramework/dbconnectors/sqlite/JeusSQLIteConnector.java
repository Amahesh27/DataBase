package com.jeus.JeusFramework.dbconnectors.sqlite;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusSQLIteConnector extends JeusJDBCConnector {

	public JeusSQLIteConnector(String dbFilePath) throws SQLException {
		super.connect("jdbc:sqlite:" + dbFilePath, "", "");
	}
}
