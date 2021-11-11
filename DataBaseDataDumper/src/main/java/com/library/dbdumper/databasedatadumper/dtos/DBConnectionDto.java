package com.library.dbdumper.databasedatadumper.dtos;

import com.library.dbdumper.databasedatadumper.enums.DBType;

public class DBConnectionDto {
	private DBType dbtype;
	private String hostName;
	private int portNo;
	private String userName;
	private String password;
	private String schemaName;
	private String sqlitePath;
	private String serviceName;
	private String dataBaseName;

	public DBConnectionDto() {

	}

	public DBConnectionDto(DBType dbtype, String hostName, int portNo, String userName, String password,
			String schemaName) {
		super();
		this.dbtype = dbtype;
		this.hostName = hostName;
		this.portNo = portNo;
		this.userName = userName;
		this.password = password;
		this.schemaName = schemaName;
	}
	
	public DBConnectionDto(String hostName, int portNo, String userName, String password, String serviceName, DBType dbtype) {
		super();
		this.dbtype = dbtype;
		this.hostName = hostName;
		this.portNo = portNo;
		this.userName = userName;
		this.password = password;
		this.serviceName = serviceName;
	}
	
	public DBConnectionDto(String hostName, int portNo, String userName, String password, DBType dbtype, String dataBaseName) {
		super();
		this.dbtype = dbtype;
		this.hostName = hostName;
		this.portNo = portNo;
		this.userName = userName;
		this.password = password;
		this.dataBaseName = dataBaseName;
	}

	public DBType getDbtype() {
		return dbtype;
	}

	public String getHostName() {
		return hostName;
	}

	public String getPassword() {
		return password;
	}

	public int getPortNo() {
		return portNo;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setDbtype(DBType dbtype) {
		this.dbtype = dbtype;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSqlitePath() {
		return sqlitePath;
	}

	public void setSqlitePath(String sqlitePath) {
		this.sqlitePath = sqlitePath;
	}
	
	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDatabaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	

}
