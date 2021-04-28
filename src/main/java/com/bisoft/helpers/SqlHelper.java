package com.bisoft.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
	public static Connection getConnection(final String DB_URL, final String USER, final String PASS) {
		
		
		System.out.println("Connection to PostgreSQL JDBC");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			e.printStackTrace();
			return null;
		}
		System.out.println("PostgreSQL JDBC Driver successfully connected");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
			return null;
		}
		if (connection != null) {
			System.out.println("You successfully connected to database now");
		} else {
			System.out.println("Failed to make connection to database");
			return null;
		}
		return connection;
	}
	
}
