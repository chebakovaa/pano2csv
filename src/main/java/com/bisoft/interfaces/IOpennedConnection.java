package com.bisoft.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IOpennedConnection {
	ResultSet Query(String collectionQuery) throws SQLException;
}
