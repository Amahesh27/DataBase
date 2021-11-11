import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.crestech.opkey.plugin.KeywordLibrary;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;
import com.crestech.opkey.plugin.exceptionhandling.ArgumentDataInvalidException;
import com.library.dbdumper.databasedatadumper.core.dbimplementation.DBCommandProccesor;
import com.library.dbdumper.databasedatadumper.daos.DBDumper;
import com.library.dbdumper.databasedatadumper.dtos.DBConnectionDto;
import com.library.dbdumper.databasedatadumper.dtos.TableDataDto;
import com.library.dbdumper.databasedatadumper.dtos.TableHeaderDataDto;
import com.library.dbdumper.databasedatadumper.enums.DBType;
import com.library.dbdumper.databasedatadumper.exceptions.DBNotImplementedException;
import com.library.dbdumper.databasedatadumper.utils.DBDumperUtilities;

public class DBComparisonKeywords implements KeywordLibrary {
	
	public FunctionResult dbConnectToDb(String dbtype, String hostName, int portNo, String userName, String password,
			String schema) {
		return getDBConnection(dbtype, hostName, portNo, userName, password, schema);
	}
	
	public FunctionResult dbConnectToMySqlDb(String hostName, int portNo, String userName, String password,
			String serviceName) {
		String dbtype = "MYSQL";
		return getDBConnection(dbtype, hostName, portNo, userName, password, serviceName);
	}
	
	public FunctionResult dbConnectToOracleDB(String hostName, int portNo, String userName, String password,
			String serviceName) {
		String dbtype = "ORACLEDB";
		return getDBConnection(dbtype, hostName, portNo, userName, password, serviceName);
	}
	
	public FunctionResult dbConnectToMSsqlDB(String hostName, int portNo, String userName, String password,
			String dataBaseName) {
		String dbtype = "MSSQL";
		return getDBConnection(dbtype, hostName, portNo, userName, password, dataBaseName);
	}
	
	public FunctionResult dbConnectToPostGres(String hostName, int portNo, String userName, String password,
			String dataBaseName) {
		String dbtype = "POSTGRESSQL";
		return getDBConnection(dbtype, hostName, portNo, userName, password, dataBaseName);
	}

	public FunctionResult dbConnectToSqlite(String sqlitePath, String password) {
		if(DBDumperUtilities.isStringNullOrBlank(sqlitePath)) {
			return Result.PASS().setMessage("Argument: sqlitepath is blank.").setOutput("").make();
		}
		UUID dbcid;
		DBConnectionDto dto = new DBConnectionDto();
		dto.setDbtype(DBType.SQLITE);
		try {
			dto.setSqlitePath(sqlitePath);
			dto.setPassword(password);
			dbcid = ConnectionManager.get().addDBConnection(dto);
		}catch(Exception e) {
			return Result.PASS().setMessage("Not able to connect to database").setOutput("").make();
		}
		return Result.PASS().setOutput(dbcid.toString()).setMessage("Done").make();
	}
	
	public FunctionResult dbGetTableRowCount(String connectionId, String tableName)
			throws ArgumentDataInvalidException, SQLException, DBNotImplementedException {
		FunctionResult result = null;
		Object connectionObj=null;
		long rowCount = 0;
		try {
			if(DBDumperUtilities.isStringNullOrBlank(connectionId)) {
				return Result.PASS().setMessage("Argument: ConnectionId is blank.").setOutput(false).make();
			}
			if(DBDumperUtilities.isStringNullOrBlank(tableName)) {
				return Result.PASS().setMessage("Argument: TableName is blank.").setOutput(false).make();
			}
			
			DBConnectionDto dto = ConnectionManager.get().getDBConnection(UUID.fromString(connectionId));
			DBDumper dbDumper = new DBDumper(dto);
			
			 connectionObj = dbDumper.validateAndGetConnectionObj(tableName);
			
			if(connectionObj instanceof DBCommandProccesor) {
				rowCount = dbDumper.getTableRowCount((DBCommandProccesor) connectionObj, tableName);
			} else {
				return Result.PASS().setMessage(connectionObj.toString()).setOutput("").make();
			}
	
			if(rowCount==0) {
				return Result.PASS().setMessage("No record found").setOutput(false).make();
			}
			result = Result.PASS().setOutput(rowCount).make();
		}finally {
			DBDumperUtilities.closeConnection(connectionObj);    
		}
		return result;
	}

	public FunctionResult dbCompareTableHeaders(String connectionId1, String connectionId2, String tableName1,
			String tableName2) throws ArgumentDataInvalidException, SQLException, DBNotImplementedException {
		FunctionResult result = null;
		Object connectionObj1=null;
		Object connectionObj2=null;
		try {
			if(DBDumperUtilities.isStringNullOrBlank(connectionId1) || DBDumperUtilities.isStringNullOrBlank(connectionId2)) {
				return Result.PASS().setMessage("Argument(s): (ConnectionId1,connectionId2) is/are blank.").setOutput(false).make();
			}
			
			if(DBDumperUtilities.isStringNullOrBlank(tableName1) || DBDumperUtilities.isStringNullOrBlank(tableName2)) {
				return Result.PASS().setMessage("Argument(s): (TableName1,TableName2) is/are blank.").setOutput(false).make();
			}
			
			DBConnectionDto dto1 = ConnectionManager.get().getDBConnection(UUID.fromString(connectionId1));
			DBConnectionDto dto2 = ConnectionManager.get().getDBConnection(UUID.fromString(connectionId2));
			
			DBDumper dbDumper1 = new DBDumper(dto1);
			DBDumper dbDumper2 = new DBDumper(dto2);
			
			connectionObj1 = dbDumper1.validateAndGetConnectionObj(tableName1);
			connectionObj2 = dbDumper2.validateAndGetConnectionObj(tableName2);
			
			if(connectionObj1 instanceof DBCommandProccesor && connectionObj2 instanceof DBCommandProccesor) {
				List<TableHeaderDataDto> table1Headers = dbDumper1.getTableHeader((DBCommandProccesor) connectionObj1, tableName1);
				List<TableHeaderDataDto> table2Headers = dbDumper2.getTableHeader((DBCommandProccesor) connectionObj2, tableName2);
				boolean isEqual = table1Headers.equals(table2Headers);
				if(isEqual) {
					result = Result.PASS().setOutput(true).make();
				} else {
					result = Result.PASS().setMessage("Table Columns match not found").setOutput(false).make();
				}
			} else {
				if(connectionObj1 instanceof String) {
					result = Result.PASS().setMessage(connectionObj1.toString()).setOutput("").make();
				}
				if(connectionObj2 instanceof String) {
					result = Result.PASS().setMessage(connectionObj2.toString()).setOutput("").make();
				}
			}
		}finally {
			DBDumperUtilities.closeConnection(connectionObj1);
			DBDumperUtilities.closeConnection(connectionObj2);
		}
		return result;
	}

	public FunctionResult dbCompareTableData(String connectionId1, String connectionId2, String tableName1,
			String tableName2) throws ArgumentDataInvalidException, SQLException, DBNotImplementedException {
		FunctionResult result = null;
		Object connectionObj1 = null;
		Object connectionObj2 = null;
		try {
			if (DBDumperUtilities.isStringNullOrBlank(connectionId1) || DBDumperUtilities.isStringNullOrBlank(connectionId2)) {
				return Result.PASS().setMessage("Argument(s): (ConnectionId1,connectionId2) is/are blank.").setOutput(false).make();
			}

			if (DBDumperUtilities.isStringNullOrBlank(tableName1) || DBDumperUtilities.isStringNullOrBlank(tableName2)) {
				return Result.PASS().setMessage("Argument(s):(TableName1,Tablename2) is/are blank.").setOutput(false).make();
			}

			DBConnectionDto dto1 = ConnectionManager.get().getDBConnection(UUID.fromString(connectionId1));
			DBConnectionDto dto2 = ConnectionManager.get().getDBConnection(UUID.fromString(connectionId2));

			DBDumper dbDumper1 = new DBDumper(dto1);
			DBDumper dbDumper2 = new DBDumper(dto2);

			connectionObj1 = dbDumper1.validateAndGetConnectionObj(tableName1);
			connectionObj2 = dbDumper2.validateAndGetConnectionObj(tableName2);

			if (connectionObj1 instanceof DBCommandProccesor && connectionObj2 instanceof DBCommandProccesor) {
				long table1RowCount = dbDumper1.getTableRowCount((DBCommandProccesor) connectionObj1, tableName1);
				long table2RowCount = dbDumper2.getTableRowCount((DBCommandProccesor) connectionObj2, tableName2);
				if (table1RowCount != table2RowCount) {
					return Result.PASS().setMessage("Table Rows match not found").setOutput(false).make();
				}
				
				List<TableHeaderDataDto> table1Headers = dbDumper1.getTableHeader((DBCommandProccesor) connectionObj1, tableName1);
				List<TableHeaderDataDto> table2Headers = dbDumper2.getTableHeader((DBCommandProccesor) connectionObj2, tableName2);
				if(!table1Headers.equals(table2Headers)) {
					return Result.PASS().setMessage("Table Columns match not found").setOutput(false).make();
				}
				
				TableDataDto tableDataDto1 = dbDumper1.getTableData((DBCommandProccesor) connectionObj1, tableName1, table2Headers);
				TableDataDto tableDataDto2 = dbDumper2.getTableData((DBCommandProccesor) connectionObj2, tableName2, table2Headers);
				
				if(tableDataDto1.equals(tableDataDto2)) {
					result = Result.PASS().setOutput(true).make();
				} else {
					result = Result.PASS().setMessage("Data Mismatch").setOutput(false).make();
				}
			} else {
				if (connectionObj1 instanceof String) {
					result = Result.PASS().setMessage(connectionObj1.toString()).setOutput("").make();
				}
				if (connectionObj2 instanceof String) {
					result = Result.PASS().setMessage(connectionObj2.toString()).setOutput("").make();
				}
			}
		} finally {
			DBDumperUtilities.closeConnection(connectionObj1);
			DBDumperUtilities.closeConnection(connectionObj2);
		}
		return result;
	}
	
	private FunctionResult getDBConnection(String dbtype, String hostName, int portNo, String userName, String password, String dataBaseName) {
		if (DBDumperUtilities.isStringNullOrBlank(dbtype) || DBDumperUtilities.isStringNullOrBlank(hostName) || DBDumperUtilities.isStringNullOrBlank(userName)
				|| DBDumperUtilities.isStringNullOrBlank(password) || DBDumperUtilities.isStringNullOrBlank(dataBaseName)) {
			return Result
					.PASS()
					.setMessage("Argument(s): (hostName,portNo,userName,Password,DataBaseName) is/are blank.")
					.setOutput("").make();
		}
		UUID dbcid;
		try {
			DBType dbtypeEnum = new DBDumperUtilities().getDBType(dbtype);
			DBConnectionDto dto = new DBConnectionDto(dbtypeEnum, hostName, portNo, userName, password, dataBaseName);
			dbcid = ConnectionManager.get().addDBConnection(dto);
		} catch (Exception e) {
			return Result.PASS().setMessage("Not able to connect to database").setOutput("").make();
		}
		return Result.PASS().setOutput(dbcid.toString()).setMessage("Done").make();
	}

	
	
}
