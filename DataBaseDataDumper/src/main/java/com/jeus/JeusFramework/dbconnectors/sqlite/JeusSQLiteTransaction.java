package com.jeus.JeusFramework.dbconnectors.sqlite;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusSQLiteTransaction extends JeusJDBCTransaction {
	private JeusSQLIteConnector connector;

	public JeusSQLiteTransaction(JeusSQLIteConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusSQLIteConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusSQLIteConnector connector) {
		this.connector = connector;
	}

}
