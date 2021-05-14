package com.bisoft.models;

import com.bisoft.exeptions.GetTitleObjectException;
import com.bisoft.interfaces.IModelObject;
import com.bisoft.interfaces.IOpennedConnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBModelObject implements IModelObject {

	private final String table_name;
	private final ResultSet dataSet;
	
	public DBModelObject(ResultSet dataSet, String table_name) {
		this.table_name = table_name;
		this.dataSet = dataSet;
	}
	
	@Override
	public List<String> title() throws GetTitleObjectException {
		List<String> row = new ArrayList<>();
		try {
			ResultSetMetaData meta = dataSet.getMetaData();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				row.add(meta.getColumnLabel(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GetTitleObjectException("Get Title Object Fail");
		}
		return row;
	}
	
	@Override
	public Iterable<List<String>> body() {
		return new TableBody(dataSet);
	}
	
}
