package com.bisoft.interfaces;

import java.io.IOException;
import java.sql.SQLException;

public interface ITableCollection {
    void save(IClearedFolder folder, IFileFormat format) throws IOException, SQLException;
}
