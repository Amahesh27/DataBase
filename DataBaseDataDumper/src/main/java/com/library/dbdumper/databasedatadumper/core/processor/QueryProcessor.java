package com.library.dbdumper.databasedatadumper.core.processor;

import com.library.dbdumper.databasedatadumper.constants.QueryConstants;
import com.library.dbdumper.databasedatadumper.enums.DBType;
import com.library.dbdumper.databasedatadumper.exceptions.DBNotImplementedException;

public class QueryProcessor {
	private DBType dbType;

	public QueryProcessor(DBType dbType) {
		super();
		this.dbType = dbType;
	}

	public String getQueryOfAllTableNames(String schemaName) throws DBNotImplementedException {
		if (this.dbType == DBType.MYSQL) {
			return String.format(QueryConstants.MYSQLGETALLTABLESNAMES, schemaName);
		}
		if (this.dbType == DBType.ORACLEDB) {
			return String.format(QueryConstants.ORACLEGETALLTABLESNAMES, schemaName);
		}
		if (this.dbType == DBType.AMAZONREDSHIFT) {
			return String.format(QueryConstants.AmazonRedshiftGETALLTABLESNAMES, schemaName);
		}
		if (this.dbType == DBType.DB2) {
			return String.format(QueryConstants.DB2GETALLTABLESNAMES, schemaName);
		}
		if (this.dbType == DBType.MSSQL) {
			return String.format(QueryConstants.MSSQLGETALLTABLESNAMES, schemaName);
		}
		if (this.dbType == DBType.POSTGRESSQL) {
			return String.format(QueryConstants.PostgresSQLGETALLTABLESNAMES, schemaName);
		}
		if (this.dbType == DBType.SQLITE) {
			return String.format(QueryConstants.SqliteGETALLTABLESNAMES, schemaName);
		}
		throw new DBNotImplementedException();
	}

	public String getQueryOfRowCount(String tableName) throws DBNotImplementedException {
		System.out.println("inside  QueryProcessor : getQueryOfRowCount()");
		if (this.dbType == DBType.MYSQL) {
			return String.format(QueryConstants.MYSQLGETROWCOUNT, tableName);
		}
		if (this.dbType == DBType.ORACLEDB) {
			System.out.println("*********** QueryProcessor :going to get count fromoracle table");
			return String.format(QueryConstants.ORACLEGETROWCOUNT, tableName);
		}
		if (this.dbType == DBType.AMAZONREDSHIFT) {
			return String.format(QueryConstants.AmazonRedshiftGETROWCOUNT, tableName);
		}
		if (this.dbType == DBType.DB2) {
			return String.format(QueryConstants.DB2GETROWCOUNT, tableName);
		}
		if (this.dbType == DBType.MSSQL) {
			return String.format(QueryConstants.MSSQLGETROWCOUNT, tableName);
		}
		if (this.dbType == DBType.POSTGRESSQL) {
			System.out.println("Inside getQueryOfRowCount in postgres row count");
			return String.format(QueryConstants.PostgresSQLGETROWCOUNT, tableName);
		}
		if (this.dbType == DBType.SQLITE) {
			return String.format(QueryConstants.SqliteGETROWCOUNT, tableName);
		}
		throw new DBNotImplementedException();
	}

	public String getQueryOfTableColumns(String schemaName, String tableName) throws DBNotImplementedException {
		System.out.println("Inside getQueryOfTableColumns");
		System.out.println("Inside getQueryOfTableColumns scheam name is: "+schemaName);
		System.out.println("Inside getQueryOfTableColumns table name is: "+tableName);


		if (this.dbType == DBType.MYSQL) {
			return String.format(QueryConstants.MYSQLGETTABLECOLUMNS, schemaName, tableName);
		}
		if (this.dbType == DBType.ORACLEDB) {
			System.out.println("checking for oracleDB inside getQueryOfTableColumns()");
			return String.format(QueryConstants.ORACLEGETTABLECOLUMNS, tableName);
		}
		if (this.dbType == DBType.AMAZONREDSHIFT) {
			return String.format(QueryConstants.AmazonRedshiftGETTABLECOLUMNS, schemaName, tableName);
		}
		if (this.dbType == DBType.DB2) {
			return String.format(QueryConstants.DB2GETTABLECOLUMNS, schemaName, tableName);
		}
		if (this.dbType == DBType.MSSQL) {
			return String.format(QueryConstants.MSSQLGETTABLECOLUMNS, schemaName, tableName);
		}
		if (this.dbType == DBType.POSTGRESSQL) {
			System.out.println("Inside getQueryOfTableColumns checking for POSTGRESSQL");
			return String.format(QueryConstants.PostgresSQLGETTABLECOLUMNS, schemaName, tableName);
		}
		if (this.dbType == DBType.SQLITE) {
			System.out.println("Inside getQueryOfTableColumns checking for SQLITE");
			return String.format(QueryConstants.SqliteGETTABLECOLUMNS, tableName);
		}
		throw new DBNotImplementedException();
	}

	public String getQueryOfTableDatas(String schemaName, String tableName) throws DBNotImplementedException {
		if (this.dbType == DBType.MYSQL) {
			return String.format(QueryConstants.MYSQLGETALLDATAS, tableName);
		}
		if (this.dbType == DBType.ORACLEDB) {
			return String.format(QueryConstants.ORACLEGETALLDATAS, tableName);
		}
		if (this.dbType == DBType.AMAZONREDSHIFT) {
			return String.format(QueryConstants.AmazonRedshiftGETALLDATAS, tableName);
		}
		if (this.dbType == DBType.DB2) {
			return String.format(QueryConstants.DB2GETALLDATAS, tableName);
		}
		if (this.dbType == DBType.MSSQL) {
			return String.format(QueryConstants.MSSQLGETALLDATAS, tableName);
		}
		if (this.dbType == DBType.POSTGRESSQL) {
			StringBuilder pgTableName = new StringBuilder();
			pgTableName.append("\"").append(schemaName).append("\"").append(".").append("\"").append(tableName)
			.append("\"");
			return String.format(QueryConstants.PostgresSQLGETALLDATAS, pgTableName.toString());
		}
		if (this.dbType == DBType.SQLITE) {
			return String.format(QueryConstants.SqliteGETALLDATAS, tableName);
		}
		throw new DBNotImplementedException();
	}
	
	public String getIsTableExistsQuery(String schemaName, String tableName) throws DBNotImplementedException {
		
		if (this.dbType == DBType.MYSQL) {
			return String.format(QueryConstants.MYSQLTABLEEXISTS, schemaName, tableName);
		}
		
		if (this.dbType == DBType.ORACLEDB) {
			System.out.println("checking for oracleDB inside getIsTableExistsQuery");
			return String.format(QueryConstants.ORACLETABLEEXISTS, tableName);
		}
		
		if (this.dbType == DBType.MSSQL) {
			System.out.println("checking for mssql inside getIsTableExistsQuery");
			return String.format(QueryConstants.MSSQLTABLEEXISTS, schemaName,tableName);
		}
		
		if (this.dbType == DBType.SQLITE) {
			System.out.println("checking for sqlite inside getIsTableExistsQuery");
			return String.format(QueryConstants.SqliteTABLEEXISTS, tableName);
		}
		
		if (this.dbType == DBType.POSTGRESSQL) {
			System.out.println("checking for postgress inside getIsTableExistsQuery");
			return String.format(QueryConstants.PostgresTABLEEXISTS, schemaName, tableName);
		}
		
		
		throw new DBNotImplementedException();
	}
	
	public String getIsSchemaExistsQuery(String schemaName) throws DBNotImplementedException {
		if (this.dbType == DBType.MYSQL) {
			return String.format(QueryConstants.MYSQLSCHEMAEXISTS, schemaName);
		}
		
		if (this.dbType == DBType.ORACLEDB) {
			System.out.println("checking for oracledb inside  getIsSchemaExistsQuery");
			return String.format(QueryConstants.ORACLESCHEMAEXISTS);
		}
		if (this.dbType == DBType.MSSQL) {
			System.out.println("checking for mssql inside  getIsSchemaExistsQuery");
			return String.format(QueryConstants.MSSQLSCHEMAEXISTS,schemaName);
		}
		
		if (this.dbType == DBType.POSTGRESSQL) {
			System.out.println("checking for postgres inside  getIsSchemaExistsQuery");
			return String.format(QueryConstants.PostgresSCHEMAEXISTS, schemaName);
		}
		throw new DBNotImplementedException();
	}
}
