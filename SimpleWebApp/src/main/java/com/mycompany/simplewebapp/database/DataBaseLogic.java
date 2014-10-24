package com.mycompany.simplewebapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseLogic {
		
	
	public Connection getDatabaseConnection(String driverName, String dbUrl, String dbUsername, String dbPassword){
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	

}
