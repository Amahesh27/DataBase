package com.library.dbdumper.databasedatadumper.core.dbimplementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jeus.JeusFramework.dbconnectors.amazonredshift.JeusAmazonRedshiftSQLConnector;
import com.jeus.JeusFramework.dbconnectors.db2.JeusDB2SQLConnector;
import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;
import com.jeus.JeusFramework.dbconnectors.mssql.JeusMsSqlDbConnector;
import com.jeus.JeusFramework.dbconnectors.mysql.JeusMYSQLConnector;
import com.jeus.JeusFramework.dbconnectors.oracledb.JeusOracleDbConnector;
import com.jeus.JeusFramework.dbconnectors.postgresql.JeusPostgreSQLConnector;
import com.jeus.JeusFramework.dbconnectors.sqlite.JeusSQLIteConnector;
import com.library.dbdumper.databasedatadumper.dtos.DBConnectionDto;
import com.library.dbdumper.databasedatadumper.enums.DBType;

public class DBCommandProccesor implements AutoCloseable {
	private JeusJDBCConnector dbConnector;

	public DBCommandProccesor(DBConnectionDto dto) throws SQLException {
		System.out.println("Inside  DBCommandProccesor() ");
		if (dto.getDbtype() == DBType.MYSQL) {
			dbConnector = new JeusMYSQLConnector(dto.getHostName(), dto.getPortNo(), dto.getSchemaName(),
					dto.getUserName(), dto.getPassword());
		}
		if (dto.getDbtype() == DBType.ORACLEDB) {
			System.out.println("checking for ORACLEDB ");
			System.out.println("hostName:"+dto.getHostName());
			System.out.println("port :"+dto.getPortNo());
			System.out.println("service: "+dto.getServiceName());
			System.out.println("service: "+dto.getSchemaName());
			System.out.println("user: "+dto.getUserName());
			System.out.println("pasword: "+dto.getPassword());
			dbConnector = new JeusOracleDbConnector(dto.getHostName(), dto.getPortNo(), dto.getSchemaName(),
					dto.getUserName(), dto.getPassword());
		}

		if (dto.getDbtype() == DBType.AMAZONREDSHIFT) {
			dbConnector = new JeusAmazonRedshiftSQLConnector(dto.getHostName(), dto.getPortNo(), dto.getSchemaName(),
					dto.getUserName(), dto.getPassword());
		}
		if (dto.getDbtype() == DBType.DB2) {
			dbConnector = new JeusDB2SQLConnector(dto.getHostName(), dto.getPortNo(), dto.getSchemaName(),
					dto.getUserName(), dto.getPassword());
		}
		if (dto.getDbtype() == DBType.MSSQL) {
			dbConnector = new JeusMsSqlDbConnector(dto.getHostName(), dto.getPortNo(), dto.getSchemaName(),
					dto.getUserName(), dto.getPassword());
		}
		if (dto.getDbtype() == DBType.POSTGRESSQL) {
			dbConnector = new JeusPostgreSQLConnector(dto.getHostName(), dto.getPortNo(), dto.getDataBaseName(),
					dto.getUserName(), dto.getPassword());
		}
		if (dto.getDbtype() == DBType.SQLITE) {
			System.out.println("checking for SQLITE");
			dbConnector = new JeusSQLIteConnector(dto.getSqlitePath());
			
			if(dbConnector instanceof JeusSQLIteConnector) {
				System.out.println("Instance of SQLITE");
			}
		}
	}

	@Override
	public void close() throws SQLException {
		dbConnector.close();
	}

	public ResultSet executeSqlQuery(String query) throws SQLException {
		return dbConnector.executeQuery(query);
	}
}
