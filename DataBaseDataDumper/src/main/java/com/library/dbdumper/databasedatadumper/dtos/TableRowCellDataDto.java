package com.library.dbdumper.databasedatadumper.dtos;

import java.util.Objects;

public class TableRowCellDataDto {

	public TableRowCellDataDto() {
		super();
	}

	private String columnName;
	private String dataType;
	private Object value;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnName, dataType, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableRowCellDataDto other = (TableRowCellDataDto) obj;
		return Objects.equals(columnName, other.columnName) && Objects.equals(dataType, other.dataType)
				&& Objects.equals(value, other.value);
	}
	
}
