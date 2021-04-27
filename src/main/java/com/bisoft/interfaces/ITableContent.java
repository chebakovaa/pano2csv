package com.bisoft.interfaces;

import java.io.IOException;
import java.sql.SQLException;

public interface ITableContent {
	void save(ISavedFormat format) throws SQLException, IOException;
}
