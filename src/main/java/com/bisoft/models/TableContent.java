package com.bisoft.models;

import com.bisoft.interfaces.IFolderContent;
import com.bisoft.interfaces.ISavedFormat;
import com.bisoft.interfaces.ITableContent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableContent implements ITableContent {
	private final Connection connection;
	private final String tableName;
	
	public TableContent(Connection connection, String tableName) {
		this.connection = connection;
		this.tableName = tableName;
	}
	
	public void save(ISavedFormat format) throws SQLException, IOException {
		List<String> cols = new ArrayList();
		ResultSet resultRows = connection.createStatement().executeQuery(String.format("select * from neo.%s", tableName));
		ResultSetMetaData meta = resultRows.getMetaData();
		int count = meta.getColumnCount();
		for(int i=1; i<=count; i++){
			cols.add(meta.getColumnLabel(i));
		}
		format.saveNext(cols);
		while (resultRows.next()) {
			cols.clear();
			for(int i=1; i<=count; i++){
				cols.add(resultRows.getString(i));
			}
			format.saveNext(cols);
		}
	}
}
