package com.bisoft.interfaces;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITableContent {
	void save(ResultSet resultSet, ISavedFormat format) throws SQLException, IOException;
}
