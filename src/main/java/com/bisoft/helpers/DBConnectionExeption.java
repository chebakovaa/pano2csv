package com.bisoft.helpers;

public class DBConnectionExeption  extends Exception{
    public DBConnectionExeption(String errorMessage){
        super(errorMessage);
    }
}
