package com.bioshirt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
	
	//create the database
	
	//method for inserting test data
	
	private static DatabaseHelper databaseHelper;
	private Connection connection;
	private DatabaseHelper() {
		
	}
	
	public static DatabaseHelper getInstance() {
		if (databaseHelper == null ) {
			databaseHelper = new DatabaseHelper();
			return databaseHelper;
		} else {
			return databaseHelper;
		}	
	}
	
	private Connection createConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/slipdb?user=root";
			connection = DriverManager.getConnection(url);
			//sample query, check this later	

			return connection;
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			return connection;
		}
		
	}
	
	public Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			return createConnection();
		}
	}
	
	//TODO refactor for best practices
	public ResultSet executeQuery(String sql) {
		ResultSet rs;
		connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeUpdate(String sql) {
		connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
		    ps.executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
