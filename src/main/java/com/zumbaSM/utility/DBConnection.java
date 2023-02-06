package com.zumbaSM.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	private final String DB_URL="jdbc:mysql://localhost:3306/ZumbaManagement";
	private final String DB_USERNAME ="root";
	private final String DB_PASSWORD ="joindb@555";
	
	private Connection connection;
	private Statement statement;
	
	// Register the Driver class
		public DBConnection() {
			try {
				getClass().forName("com.mysql.cj.jdbc.Driver");
				System.out.println("-- JDBC Driver Loaded. --");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-- Something Went Wrong: -- "+e);
			}
		}
		
		// Initialize DB Connection
		public void init() {
			try {
				connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
				statement = connection.createStatement();
				System.out.println("-- Connection established. --");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-- Something Went Wrong: -- "+e);
			}
		}
		
		// Close DB Connection
		public void destroy() {
			try {
				if(connection!=null) {
					connection.close();
					connection = null;
					System.out.println("-- Connection closed. --");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-- Something Went Wrong: -- "+e);
			}
		}
		
		// Get DB Connection
		public Connection getConnection() {
			return connection;
		}
		
		// select operation
		public ResultSet executeQuery(String sql) {
			ResultSet rs = null;
			try {
				System.out.println("-- Executing SQL --"+sql);
				rs = statement.executeQuery(sql);
				System.out.println("-- Query Executed successfully--");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-- Something Went Wrong: -- "+e);
			}
			return rs;
		}
		
		// save , update and delete 
		public int executeUpdate(String sql) {
			int result =0;
			try {
				System.out.println("-- Executing SQL --"+sql);
				result = statement.executeUpdate(sql);
				System.out.println("-- Query Executed successfully --");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-- Something Went Wrong: -- "+e);
			}
			return result;
		}
}
