package com.bisoft.models;

import java.sql.Connection;
import java.sql.Statement;

public class TableContent {
	private final Connection stmt;
	private final String tableName;
	
	public TableContent(Connection stmt, String tableName) {
		this.stmt = stmt;
		this.tableName = tableName;
	}
	
	public boolean toCSV() {
	}
}
