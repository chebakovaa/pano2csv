package com.bisoft.models;

import com.bisoft.exeptions.GetObjectNamesException;
import com.bisoft.interfaces.IModelObject;
import com.bisoft.interfaces.IModelSource;
import com.bisoft.interfaces.IOpennedConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class DBSource implements IModelSource {
	private final String collectionQuery;
	private final String objectQuery;
	private final IOpennedConnection openedConnection;
	
	public DBSource(IOpennedConnection openedConnection, String collectionQuery, String objectQuery) {
		this.openedConnection = openedConnection;
		this.collectionQuery = collectionQuery;
		this.objectQuery = objectQuery;
	}
	
	@Override
	public Iterator<IModelObject> objectCollection() throws GetObjectNamesException {
		try {
			ResultSet tables = openedConnection.Query(collectionQuery);
			return new ModelObjectDBCollection(openedConnection, tables, objectQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GetObjectNamesException("Get Object Names Fail");
		}
	}
}
