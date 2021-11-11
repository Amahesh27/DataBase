package com.library.dbdumper.databasedatadumper.dtos;

import java.util.Objects;

public class TableHeaderDataDto {
	private String columnLabel;
	private String columnName;
	private String columnType;

	public TableHeaderDataDto() {
		super();
	}

	public TableHeaderDataDto(String columnLabel, String columnName, String columnType) {
		super();
		this.columnLabel = columnLabel;
		this.columnName = columnName;
		this.columnType = columnType;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnName, columnType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableHeaderDataDto other = (TableHeaderDataDto) obj;
		return Objects.equals(columnName, other.columnName) && Objects.equals(columnType, other.columnType);
	}

}
