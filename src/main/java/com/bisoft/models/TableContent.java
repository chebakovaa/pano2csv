package com.bisoft.models;

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
	private final String query;
	
	public TableContent(Connection connection, String query) {
		this.connection = connection;
		this.query = query;
	}
	
	@Override
	public void save(ResultSet resultRows, ISavedFormat format) throws SQLException, IOException {
		List<String> cols = new ArrayList();
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
