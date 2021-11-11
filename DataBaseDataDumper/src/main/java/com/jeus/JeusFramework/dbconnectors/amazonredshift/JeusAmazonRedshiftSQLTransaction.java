package com.jeus.JeusFramework.dbconnectors.amazonredshift;

import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCTransaction;

public class JeusAmazonRedshiftSQLTransaction extends JeusJDBCTransaction {

	private JeusAmazonRedshiftSQLConnector connector;

	public JeusAmazonRedshiftSQLTransaction(JeusAmazonRedshiftSQLConnector connector) throws SQLException {
		super(connector);
		this.setConnector(connector);
	}

	@Override
	public JeusAmazonRedshiftSQLConnector getConnector() {
		return connector;
	}

	public void setConnector(JeusAmazonRedshiftSQLConnector connector) {
		this.connector = connector;
	}

}
