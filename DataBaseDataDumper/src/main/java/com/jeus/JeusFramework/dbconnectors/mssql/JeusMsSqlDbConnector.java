package com.jeus.JeusFramework.dbconnectors.mssql;

import java.sql.SQLException;
import com.jeus.JeusFramework.dbconnectors.jdbc.JeusJDBCConnector;

public class JeusMsSqlDbConnector extends JeusJDBCConnector {

	public JeusMsSqlDbConnector(String ip, int portNo, String dataBaseName, String userName, String password)
			throws SQLException {
		
		try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		super.connect("jdbc:sqlserver://" + ip + ":" + portNo + ";databaseName=" + schema + ";user=" + userName
//				+ ";password=" + password);
		super.connect("jdbc:sqlserver://" + ip + ":" + portNo + ";databaseName=" + dataBaseName + ";user=" + userName
				+ ";password=" + password);
	}
}
