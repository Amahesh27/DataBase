package com.jeus.JeusFramework.dbconnectors.oracledb;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusOracleDbTransaction extends JeusJDBCTransaction {

	private JeusOracleDbConnector connector;

	public JeusOracleDbTransaction(JeusOracleDbConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusOracleDbConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusOracleDbConnector connector) {
		this.connector = connector;
	}

}
