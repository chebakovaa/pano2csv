package com.bisoft.models;

import java.sql.Statement;

public class TableContent {
	private final Statement stmt;
	private final String tableName;
	
	public TableContent(Statement stmt, String tableName) {
		this.stmt = stmt;
		this.tableName = tableName;
	}
	
	public int load() {
		return 3;
	}
}
