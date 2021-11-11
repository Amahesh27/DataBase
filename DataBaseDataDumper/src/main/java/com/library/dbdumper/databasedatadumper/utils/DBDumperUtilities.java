package com.library.dbdumper.databasedatadumper.utils;

import java.sql.SQLException;
import java.util.Objects;

import com.library.dbdumper.databasedatadumper.core.dbimplementation.DBCommandProccesor;
import com.library.dbdumper.databasedatadumper.enums.DBType;

public class DBDumperUtilities {
	
	public DBType getDBType(String dbType) {
		return DBType.valueOf(dbType.toUpperCase());
	}
	public static boolean isStringNullOrBlank(String str) {
		return (str == null || "".equals(str.trim()));
	}
	public static void closeConnection(Object connectionObj) throws SQLException {
		if(!Objects.isNull(connectionObj) && connectionObj instanceof DBCommandProccesor) {
			((DBCommandProccesor)connectionObj).close();
		}
	}
}
