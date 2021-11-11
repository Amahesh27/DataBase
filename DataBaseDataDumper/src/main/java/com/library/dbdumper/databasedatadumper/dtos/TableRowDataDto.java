package com.library.dbdumper.databasedatadumper.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableRowDataDto {
	private List<TableRowCellDataDto> cells = new ArrayList<>();

	public TableRowDataDto() {
		super();
	}

	public void addCell(TableRowCellDataDto dto) {
		getCells().add(dto);
	}

	public List<TableRowCellDataDto> getCells() {
		return cells;
	}

	public void setCells(List<TableRowCellDataDto> cells) {
		this.cells = cells;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cells);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableRowDataDto other = (TableRowDataDto) obj;
		return Objects.equals(cells, other.cells);
	}

}
