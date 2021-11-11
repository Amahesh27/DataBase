package com.jeus.JeusFramework.dbconnectors.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JeusJDBCConnector implements AutoCloseable {
	private Connection connection;

	@Override
	public void close() throws SQLException {
		closeConnection();
	}

	public void closeConnection() throws SQLException {
		getConnection().close();
	}

	public void connect(String connectUrl, String userName, String passWord) throws SQLException {
		connection = DriverManager.getConnection(connectUrl, userName, passWord);
	}

	public void connect(String connectUrl) throws SQLException {
		connection = DriverManager.getConnection(connectUrl);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		return getStatement().executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		return getStatement().executeUpdate(query);
	}

	public Connection getConnection() {
		return connection;
	}

	public PreparedStatement getPreparedStatement(String query) throws SQLException {
		return getConnection().prepareStatement(query);
	}

	private Statement getStatement() throws SQLException {
		return getConnection().createStatement();
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
