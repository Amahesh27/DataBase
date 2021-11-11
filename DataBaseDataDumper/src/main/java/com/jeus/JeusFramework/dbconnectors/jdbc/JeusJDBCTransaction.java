package com.jeus.JeusFramework.dbconnectors.jdbc;

import java.sql.SQLException;

public class JeusJDBCTransaction {
	private JeusJDBCConnector connector;

	public JeusJDBCTransaction(JeusJDBCConnector connector) throws SQLException {
		this.setConnector(connector);
		connector.getConnection().setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException {
		connector.getConnection().commit();
		turnOffTransaction();
	}

	public JeusJDBCConnector getConnector() {
		return connector;
	}

	public void rollBackTransaction() throws SQLException {
		connector.getConnection().rollback();
		turnOffTransaction();
	}

	public void setConnector(JeusJDBCConnector connector) {
		this.connector = connector;
	}

	private void turnOffTransaction() throws SQLException {
		getConnector().getConnection().setAutoCommit(true);
	}
}
