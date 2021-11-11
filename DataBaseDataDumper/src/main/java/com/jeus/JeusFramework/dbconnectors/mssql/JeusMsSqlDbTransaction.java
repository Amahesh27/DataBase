package com.jeus.JeusFramework.dbconnectors.mssql;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusMsSqlDbTransaction extends JeusJDBCTransaction {

	private JeusMsSqlDbConnector connector;

	public JeusMsSqlDbTransaction(JeusMsSqlDbConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusMsSqlDbConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusMsSqlDbConnector connector) {
		this.connector = connector;
	}

}
