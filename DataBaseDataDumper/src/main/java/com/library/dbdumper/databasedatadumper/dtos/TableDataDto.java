package com.library.dbdumper.databasedatadumper.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableDataDto {
	private String tableName;
	private String schemaName;
	private String userName;

	private List<TableHeaderDataDto> tableHeaders = new ArrayList<>();
	private List<TableRowDataDto> tableRows = new ArrayList<>();

	public TableDataDto() {
		super();
	}

	public void addTableRowData(TableRowDataDto dto) {
		getTableRows().add(dto);
	}

	public List<TableHeaderDataDto> getTableHeaders() {
		return tableHeaders;
	}

	public List<TableRowDataDto> getTableRows() {
		return tableRows;
	}

	public void setTableHeaders(List<TableHeaderDataDto> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

	public void setTableRows(List<TableRowDataDto> tableRows) {
		this.tableRows = tableRows;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tableRows);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableDataDto other = (TableDataDto) obj;
		return Objects.equals(tableRows, other.tableRows);
	}
	
}
