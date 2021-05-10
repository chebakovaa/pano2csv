package com.bisoft.interfaces;

import com.bisoft.helpers.DBConnectionExeption;

public interface IAppConnection {
    IOpennedConnection opennedConnection() throws DBConnectionExeption;
}
