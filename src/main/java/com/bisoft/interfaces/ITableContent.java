package com.bisoft.interfaces;

import java.sql.SQLException;

public interface ITableContent {
	void save(ISavedFormat format) throws SQLException;
}
