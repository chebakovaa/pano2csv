package com.bisoft.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IContentSource {
    List<String> title() throws SQLException;

    Iterable<List<String>> body() throws SQLException;
}
