package com.library.dbdumper.databasedatadumper.core.iface;

import java.sql.SQLException;
import java.util.List;

import com.library.dbdumper.databasedatadumper.core.dbimplementation.DBCommandProccesor;
import com.library.dbdumper.databasedatadumper.dtos.TableDataDto;
import com.library.dbdumper.databasedatadumper.dtos.TableHeaderDataDto;
import com.library.dbdumper.databasedatadumper.exceptions.DBNotImplementedException;

public interface BaseDBDumperImpl {

	public TableDataDto getAllTableDatas(String tableName) throws SQLException, DBNotImplementedException;

	public List<String> getAllTables() throws SQLException, DBNotImplementedException;

	public List<TableHeaderDataDto> getTableHeader(String tableName) throws SQLException, DBNotImplementedException;

	public long getTableRowCount(DBCommandProccesor processor, String tableName) throws SQLException, DBNotImplementedException;
	
	public boolean isTableExists(String tableName) throws SQLException, DBNotImplementedException;
}
