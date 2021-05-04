package com.bisoft.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ITableSource {

	IContentSource  content() throws SQLException;

}
