package com.library.dbdumper.databasedatadumper;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.dbdumper.databasedatadumper.daos.DBDumper;
import com.library.dbdumper.databasedatadumper.dtos.DBConnectionDto;
import com.library.dbdumper.databasedatadumper.dtos.TableDataDto;
import com.library.dbdumper.databasedatadumper.enums.DBType;
import com.library.dbdumper.databasedatadumper.exceptions.DBNotImplementedException;

public class DBDumperTestCase {

	private Logger logger = LoggerFactory.getLogger(DBDumperTestCase.class);
	private DBConnectionDto connectionDto;

	@Before
	public void setup() {
		this.connectionDto = new DBConnectionDto(DBType.MYSQL, "localhost", 3306, "root", "root", "neondb");
	}

	@Test
	public void testMysqlRowCount() throws SQLException, DBNotImplementedException {
//		DBDumper dbdumper = new DBDumper(connectionDto);
//		dbdumper.getAllTables();
//		long rowcount = dbdumper.getTableRowCount("test_db");
//		System.out.println(rowcount);
//		dbdumper.getTableHeader("test_db");
//		TableDataDto tableDto = dbdumper.getAllTableDatas("test_db");
//
//		tableDto.getTableRows().forEach(row -> {
//			row.getCells().forEach(cell -> {
//				logger.info("Column Name {} Column Value {} Column Type {}", cell.getColumnName(), cell.getValue(),
//						cell.getDataType());
//			});
//		});
	}

}
