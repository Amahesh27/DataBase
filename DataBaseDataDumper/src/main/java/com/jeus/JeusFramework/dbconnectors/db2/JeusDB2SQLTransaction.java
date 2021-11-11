package com.jeus.JeusFramework.dbconnectors.db2;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusDB2SQLTransaction extends JeusJDBCTransaction {

	private JeusDB2SQLConnector connector;

	public JeusDB2SQLTransaction(JeusDB2SQLConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusDB2SQLConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusDB2SQLConnector connector) {
		this.connector = connector;
	}

}
