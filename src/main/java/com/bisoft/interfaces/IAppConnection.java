package com.bisoft.interfaces;

import com.bisoft.exeptions.DBConnectionException;

public interface IAppConnection {
    IOpennedConnection opennedConnection() throws DBConnectionException;
}
