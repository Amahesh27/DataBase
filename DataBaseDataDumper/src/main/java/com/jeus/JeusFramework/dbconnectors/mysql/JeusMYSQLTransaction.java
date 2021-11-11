package com.jeus.JeusFramework.dbconnectors.mysql;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusMYSQLTransaction extends JeusJDBCTransaction {

	private JeusMYSQLConnector connector;

	public JeusMYSQLTransaction(JeusMYSQLConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusMYSQLConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusMYSQLConnector connector) {
		this.connector = connector;
	}

}
