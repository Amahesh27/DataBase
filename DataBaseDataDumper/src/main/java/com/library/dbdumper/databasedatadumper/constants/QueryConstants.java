package com.library.dbdumper.databasedatadumper.constants;

public class QueryConstants {
	// MySql Queries
	public static final String MYSQLGETROWCOUNT = "SELECT Count(*) from `%s`";
	public static final String MYSQLGETALLTABLESNAMES = "Select Table_Name from information_schema.tables where table_schema='%s'";
	public static final String MYSQLGETTABLECOLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'";
	public static final String MYSQLGETALLDATAS = "SELECT * FROM `%s`";
	public static final String MYSQLTABLEEXISTS = "SELECT count(*) FROM information_schema.TABLES WHERE (TABLE_SCHEMA = '%s') AND (TABLE_NAME = '%s') LIMIT 1";
	public static final String MYSQLSCHEMAEXISTS = "SELECT count(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s' LIMIT 1";
	
	// Oracle Queries
	public static final String ORACLEGETROWCOUNT = "SELECT Count(*) from %s";
	public static final String ORACLEGETALLTABLESNAMES = "SELECT  table_name FROM  all_tables WHERE owner='%s'";
	public static final String ORACLEGETTABLECOLUMNS = "select column_name, data_type from all_tab_columns where table_name = '%s'";
	public static final String ORACLEGETALLDATAS = "SELECT * FROM %s";
	public static final String ORACLETABLEEXISTS="select count(*) from user_tables where table_name='%s'";
	public static final String ORACLESCHEMAEXISTS="SELECT * FROM all_users";
	
	// PostgresSQL Queries
	//public static final String PostgresSQLGETROWCOUNT = "SELECT Count(*) from %s";
	public static final String PostgresSQLGETROWCOUNT = "SELECT Count(*) from  %s";
	public static final String PostgresSQLGETALLTABLESNAMES = "Select Table_Name from information_schema.tables where table_schema='%s'";
	public static final String PostgresSQLGETTABLECOLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'";
	public static final String PostgresSQLGETALLDATAS = "SELECT * FROM %s";
	public static final String PostgresTABLEEXISTS = "SELECT count(*) FROM information_schema.TABLES WHERE (TABLE_SCHEMA = '%s') AND (TABLE_NAME = '%s')";
	public static final String PostgresSCHEMAEXISTS = "SELECT count(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s' LIMIT 1";

	// MSSQL Queries
	public static final String MSSQLGETROWCOUNT = "SELECT Count(*) from %s";
	public static final String MSSQLGETALLTABLESNAMES = "Select Table_Name from information_schema.tables where TABLE_CATALOG='%s'";
	public static final String MSSQLGETTABLECOLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'";
	public static final String MSSQLGETALLDATAS = "SELECT * FROM %s";
	public static final String MSSQLTABLEEXISTS="SELECT count(*) FROM information_schema.TABLES WHERE (TABLE_SCHEMA = '%s') AND (TABLE_NAME = '%s')";
	public static final String MSSQLSCHEMAEXISTS = "SELECT count(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s'";

	// AmazonRedshift Queries
	public static final String AmazonRedshiftGETROWCOUNT = "SELECT Count(*) from %s";
	public static final String AmazonRedshiftGETALLTABLESNAMES = "Select Table_Name from information_schema.tables where table_schema='%s'";
	public static final String AmazonRedshiftGETTABLECOLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'";
	public static final String AmazonRedshiftGETALLDATAS = "SELECT * FROM %s";

	// DB2 Queries
	public static final String DB2GETROWCOUNT = "SELECT Count(*) from %s";
	public static final String DB2GETALLTABLESNAMES = "Select Table_Name from information_schema.tables where table_schema='%s'";
	public static final String DB2GETTABLECOLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='%s' AND TABLE_NAME='%s'";
	public static final String DB2GETALLDATAS = "SELECT * FROM %s";

	// Sqlite Queries
	public static final String SqliteGETROWCOUNT = "SELECT Count(*) from %s";
	public static final String SqliteGETALLTABLESNAMES = "Select Table_Name from information_schema.tables where table_schema='%s'";
	public static final String SqliteGETTABLECOLUMNS = "SELECT * FROM %s limit 0";
	public static final String SqliteGETALLDATAS = "SELECT * FROM %s";
	public static final String SqliteTABLEEXISTS =" SELECT count(name) FROM sqlite_master WHERE name='%s'";
}
