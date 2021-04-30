package com.bisoft.interfaces;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITableContent {
	void save(ITableSource source, ISavedFormat format) throws IOException;
}
