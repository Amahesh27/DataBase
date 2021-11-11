package com.library.dbdumper.databasedatadumper.daos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.dbdumper.databasedatadumper.core.dbimplementation.DBCommandProccesor;
import com.library.dbdumper.databasedatadumper.core.iface.BaseDBDumperImpl;
import com.library.dbdumper.databasedatadumper.core.processor.QueryProcessor;
import com.library.dbdumper.databasedatadumper.dtos.DBConnectionDto;
import com.library.dbdumper.databasedatadumper.dtos.TableDataDto;
import com.library.dbdumper.databasedatadumper.dtos.TableHeaderDataDto;
import com.library.dbdumper.databasedatadumper.dtos.TableRowCellDataDto;
import com.library.dbdumper.databasedatadumper.dtos.TableRowDataDto;
import com.library.dbdumper.databasedatadumper.enums.DBType;
import com.library.dbdumper.databasedatadumper.exceptions.DBNotImplementedException;

public class DBDumper implements BaseDBDumperImpl {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private DBConnectionDto dbConnectionDto;

	public DBDumper(DBConnectionDto dbConnectionDto) {
		super();
		this.dbConnectionDto = dbConnectionDto;
	}

	@Override
	public TableDataDto getAllTableDatas(String tableName) throws SQLException, DBNotImplementedException {
		TableDataDto tableDto = new TableDataDto();
		tableDto.setTableName(tableName);
		List<TableHeaderDataDto> headers = getTableHeader(tableName);
		tableDto.setTableHeaders(headers);
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			String query;
			if (dbConnectionDto.getDbtype() == DBType.POSTGRESSQL) {
				String[] tokens = tableName.split("\\.");
				query = new QueryProcessor(dbConnectionDto.getDbtype()).getQueryOfTableDatas(tokens[0],tokens[1]);
			}else {
			 query = new QueryProcessor(dbConnectionDto.getDbtype())
					.getQueryOfTableDatas(dbConnectionDto.getSchemaName(), tableName);
			}
			ResultSet rs = processor.executeSqlQuery(query);
			while (rs.next()) {
				TableRowDataDto rowDto = new TableRowDataDto();
				tableDto.addTableRowData(rowDto);
				headers.forEach(header -> {
					TableRowCellDataDto cellDto = new TableRowCellDataDto();
					cellDto.setColumnName(header.getColumnName());
					cellDto.setDataType(header.getColumnType());
					//System.out.println("*************Inside getAllTableDatas value is "+header.getColumnType());
					try {
						//System.out.println("*************Inside getAllTableDatas try block value is "+rs.getObject(header.getColumnName()));
						cellDto.setValue(rs.getObject(header.getColumnName()));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					rowDto.addCell(cellDto);
					//tableDto.addTableRowData(rowDto);
					
				});
			}
		}
		return tableDto;
	}
	
	public TableDataDto getTableData(DBCommandProccesor processor, String tableName,
			List<TableHeaderDataDto> tableHeaders) throws SQLException, DBNotImplementedException {
		TableDataDto tableDto = new TableDataDto();
		tableDto.setTableName(tableName);
		tableDto.setTableHeaders(tableHeaders);
		String query = new QueryProcessor(dbConnectionDto.getDbtype()).getQueryOfTableDatas(dbConnectionDto.getSchemaName(), tableName);
		ResultSet rs = processor.executeSqlQuery(query);
		while (rs.next()) {
			TableRowDataDto rowDto = new TableRowDataDto();
			tableDto.addTableRowData(rowDto);
			for(TableHeaderDataDto header : tableHeaders) {
				TableRowCellDataDto cellDto = new TableRowCellDataDto();
				cellDto.setColumnName(header.getColumnName());
				cellDto.setDataType(header.getColumnType());
				cellDto.setValue(rs.getObject(header.getColumnName()));
				rowDto.addCell(cellDto);
			}
		}
		return tableDto;
	}
	
	
	
//	@Override
//	public TableDataDto getAllTableDatas(String tableName) throws SQLException, DBNotImplementedException {
//		TableDataDto tableDto = new TableDataDto();
//		tableDto.setTableName(tableName);
//		tableDto.setSchemaName(dbConnectionDto.getSchemaName());
//		List<TableHeaderDataDto> headers = getTableHeader(tableName);
//		System.out.println("Size of headers - "+ headers.size());
//		tableDto.setTableHeaders(headers);
//		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
//			String query = new QueryProcessor(dbConnectionDto.getDbtype())
//					.getQueryOfTableDatas(dbConnectionDto.getSchemaName(), tableName);
//			ResultSet rs = processor.executeSqlQuery(query);
//			while (rs.next()) {
//				TableRowDataDto rowDto = new TableRowDataDto();
//				tableDto.addTableRowData(rowDto);
//				headers.forEach(header -> {
//					TableRowCellDataDto cellDto = new TableRowCellDataDto();
//					cellDto.setColumnName(header.getColumnName());
//					cellDto.setDataType(header.getColumnType());
//					try {
//						cellDto.setValue(rs.getObject(header.getColumnName()));
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					rowDto.addCell(cellDto);
//					//tableDto.addTableRowData(rowDto);
//				});
//				
//			}
//		}
//		return tableDto;i
//	}

	@Override
	public List<String> getAllTables() throws SQLException, DBNotImplementedException {
		List<String> tableNames = new ArrayList<String>();
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			String query = new QueryProcessor(dbConnectionDto.getDbtype())
					.getQueryOfAllTableNames(dbConnectionDto.getSchemaName());
			ResultSet rs = processor.executeSqlQuery(query);
			while (rs.next()) {
				String data = rs.getString(1);
				tableNames.add(data);
			}
		}
		return tableNames;
	}

	@Override
	public List<TableHeaderDataDto> getTableHeader(String tableName) throws SQLException, DBNotImplementedException {
		List<TableHeaderDataDto> headers = new ArrayList<TableHeaderDataDto>();
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			 String query;
			
//			 String query = new QueryProcessor(dbConnectionDto.getDbtype())
//					.getQueryOfTableColumns(dbConnectionDto.getSchemaName(), tableName);
			 
			 if(dbConnectionDto.getDbtype()==DBType.MSSQL || dbConnectionDto.getDbtype()==DBType.POSTGRESSQL ) {
				// System.out.println("Inside  getTableHeader checking for SQLITE");
				 String shema="";
				 String tablename="";
				 String arr[]=tableName.split("\\.",2);
				 shema=arr[0];
				 tablename=arr[1];
				 //System.out.println("Schema name is:"+shema);
				 //System.out.println("Table name is:"+tableName);
				 dbConnectionDto.setSchemaName(shema);
				 query = new QueryProcessor(dbConnectionDto.getDbtype())
							.getQueryOfTableColumns(dbConnectionDto.getSchemaName(), tablename);
			}else {
				    query = new QueryProcessor(dbConnectionDto.getDbtype())
							.getQueryOfTableColumns(dbConnectionDto.getSchemaName(), tableName);
			}
			//System.out.println("Query to be executed is : "+query);
			ResultSet rs = processor.executeSqlQuery(query);

			if (dbConnectionDto.getDbtype() == DBType.SQLITE) {
				ResultSetMetaData mrs = rs.getMetaData();
				for (int i = 1; i <= mrs.getColumnCount(); i++) {
					String columnName = mrs.getColumnName(i);
					String columnLabel = mrs.getColumnLabel(i);
					String columnDataType = mrs.getColumnTypeName(i);
//					System.out.println("columnLabel name is : " + columnLabel);
					System.out.println("column Name is : " + columnName);
					System.out.println("columnDataType is : " + columnDataType);
					logger.info("Column Info {} {} {}", columnLabel, columnName, columnDataType);
					headers.add(new TableHeaderDataDto(columnLabel, columnName, columnDataType));
				}

			} else {
				while (rs.next()) {
					String columnLabel = rs.getString(1);
					String columnName = rs.getString(1);
					System.out.println("columnLabel name is : "+columnLabel);
					System.out.println("column Name is : "+columnName);
					
					String columnDataType = rs.getString(2).toUpperCase();
					
					System.out.println("columnDataType is : "+columnDataType);
					logger.info("Column Info {} {} {}", columnLabel, columnName, columnDataType);
					headers.add(new TableHeaderDataDto(columnLabel, columnName, columnDataType));
				}
			}
			
		}
		System.out.println("table name is : "+tableName);
		System.out.println("Element inside list is : "+headers);
		return headers;
	}

	public List<TableHeaderDataDto> getTableHeader(DBCommandProccesor processor, String tableName)
			throws SQLException, DBNotImplementedException {
		List<TableHeaderDataDto> headers = new ArrayList<>();
		String query = new QueryProcessor(dbConnectionDto.getDbtype()).getQueryOfTableColumns(dbConnectionDto.getSchemaName(), tableName);
		ResultSet rs = processor.executeSqlQuery(query);
		while (rs.next()) {
			String columnLabel = rs.getString(1);
			String columnName = rs.getString(1);
			String columnDataType = rs.getString(2).toUpperCase();
			logger.info("Column Info {} {} {}", columnLabel, columnName, columnDataType);
			headers.add(new TableHeaderDataDto(columnLabel, columnName, columnDataType));
		}
		return headers;
	}

	public long getTableRowCount(DBCommandProccesor processor, String tableName)
			throws SQLException, DBNotImplementedException {
		long rowCount = 0;
		String query = new QueryProcessor(dbConnectionDto.getDbtype()).getQueryOfRowCount(tableName);
		ResultSet rs = processor.executeSqlQuery(query);
		while (rs.next()) {
			rowCount = rs.getLong(1);
		}
		return rowCount;
	}

	@Override
	public boolean isTableExists(String tableName) throws SQLException, DBNotImplementedException {
		boolean isTableExists = false;
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			String query;
			if(dbConnectionDto.getDbtype()==DBType.MSSQL || dbConnectionDto.getDbtype()==DBType.POSTGRESSQL ) {
				 System.out.println("Inside is table exist checking for MSSQL and Postgressql");
				 String shema="";
				 String tablename="";
				 String arr[]=tableName.split("\\.",2);
				 shema=arr[0];
				 tablename=arr[1];
//				 System.out.println("Schema name is:"+shema);
//				 System.out.println("Table name is:"+tableName);
				 dbConnectionDto.setSchemaName(shema);
				 query = new QueryProcessor(dbConnectionDto.getDbtype())
							.getIsTableExistsQuery(dbConnectionDto.getSchemaName(), tablename);
			}else {
				   query = new QueryProcessor(dbConnectionDto.getDbtype())
						.getIsTableExistsQuery(dbConnectionDto.getSchemaName(), tableName);
			}
			System.out.println("Ouery executed and result is:"+query);
			ResultSet rs = processor.executeSqlQuery(query);
			if (rs != null) {
				rs.next();
				isTableExists = (rs.getInt(1) == 1);
			}
		}
	System.out.println("value of isTableExist:"+isTableExists);
		return isTableExists;
	}
	
	private boolean isTableExists(DBCommandProccesor processor, String tableName) throws SQLException, DBNotImplementedException {
		boolean isTableExists = false;
		String query = new QueryProcessor(dbConnectionDto.getDbtype()).getIsTableExistsQuery(dbConnectionDto.getSchemaName(), tableName);
		ResultSet rs = processor.executeSqlQuery(query);
		if (rs != null) {
			rs.next();
			isTableExists = (rs.getInt(1) == 1);
		}
		return isTableExists;
	}
	
	public boolean isSchemaExists()  throws SQLException, DBNotImplementedException{
		boolean isSchemaExists = false;
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			String query = new QueryProcessor(dbConnectionDto.getDbtype())
					.getIsSchemaExistsQuery(dbConnectionDto.getSchemaName());
			ResultSet rs = processor.executeSqlQuery(query);
			if (rs != null) {
				rs.next();
				isSchemaExists = (rs.getInt(1) == 1);
			}
		}  catch(Exception e) {
			
		}
		return isSchemaExists;	
	}
	
	private boolean isSchemaExists(DBCommandProccesor processor) throws DBNotImplementedException, SQLException{
		boolean isSchemaExists = false;
			String query = new QueryProcessor(dbConnectionDto.getDbtype()).getIsSchemaExistsQuery(dbConnectionDto.getSchemaName());
			ResultSet rs = processor.executeSqlQuery(query);
			if (rs != null) {
				rs.next();
				isSchemaExists = (rs.getInt(1) == 1);
			}
		return isSchemaExists;	
	}
	
	public boolean isUserExists()  throws SQLException, DBNotImplementedException{
		boolean isUserExists = false;
		List<String> users = new ArrayList<>();
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			String query = new QueryProcessor(dbConnectionDto.getDbtype())
					.getIsSchemaExistsQuery(dbConnectionDto.getUserName());
			System.out.println("query for isuserExist():"+query);

			ResultSet rs = processor.executeSqlQuery(query);
			if (rs != null) {
				rs.next();
				users.add(rs.getString(1));
			}
			System.out.println("All users :"+users);
			isUserExists=users.contains(dbConnectionDto.getUserName());
			System.out.println("users exist or not :"+isUserExists);

		}  catch(Exception e) {
			
		}
		return isUserExists;	
	}
	
	public boolean isConnectionSuccessful(){
		try (DBCommandProccesor processor = new DBCommandProccesor(dbConnectionDto)) {
			// do nothing
		} catch (Exception e){
			return false;
		}	
		return true;
	}
	
	public Object validateAndGetConnectionObj(String tableName) throws DBNotImplementedException, SQLException {
		DBCommandProccesor processor = getDbConnectionObj();
		if(processor == null) {
			return "Not able to connect to database because of any/all mention reasion- HostName, UserId, Password is/are invalid (or) Server is not present on given host.";
		} else {
			boolean isisSchemaExists = isSchemaExists(processor);
			if(isisSchemaExists) {
				boolean  isTableExists = isTableExists(processor, tableName);
				if(isTableExists) {
					return processor;
				} else {
					return "Table: "+tableName+" is not present in your schema or database";
				}
			} else {
				return "Schema name: "+ dbConnectionDto.getSchemaName()+"  is not present in your database";
			}
		}
	}
	
	private DBCommandProccesor getDbConnectionObj(){
		DBCommandProccesor processor=null;
		try {
			processor = new DBCommandProccesor(dbConnectionDto);
		} catch (Exception e){
			return null;
		}
		return processor;
	}
	
}
