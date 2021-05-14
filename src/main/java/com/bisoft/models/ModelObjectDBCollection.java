package com.bisoft.models;

import com.bisoft.interfaces.IModelObject;
import com.bisoft.interfaces.IOpennedConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ModelObjectDBCollection implements Iterator<IModelObject> {
	
	private final ResultSet tableNames;
	private final IOpennedConnection openedConnection;
	private final String objectQuery;
	
	public ModelObjectDBCollection(IOpennedConnection openedConnection, ResultSet tableNames, String objectQuery) {
		this.tableNames = tableNames;
		this.openedConnection = openedConnection;
		this.objectQuery = objectQuery;
	}
	
	@Override
	public boolean hasNext(){
		try {
			return tableNames.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public IModelObject next() {
		try {
			String name = tableNames.getString("table_name");
			ResultSet table = openedConnection.Query(String.format(objectQuery, name));
			return new DBModelObject(table, name);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoSuchElementException("Get Next ModelObject Fail");
		}
	}
	
}