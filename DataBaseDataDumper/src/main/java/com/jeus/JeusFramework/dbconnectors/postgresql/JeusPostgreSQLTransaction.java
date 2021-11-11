package com.jeus.JeusFramework.dbconnectors.postgresql;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusPostgreSQLTransaction extends JeusJDBCTransaction {

	private JeusPostgreSQLConnector connector;

	public JeusPostgreSQLTransaction(JeusPostgreSQLConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusPostgreSQLConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusPostgreSQLConnector connector) {
		this.connector = connector;
	}

}
