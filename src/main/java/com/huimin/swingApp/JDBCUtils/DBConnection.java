package com.huimin.swingApp.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
* Function description： create database connection 
* Author：Huimin Liu
* Create time：May 21, 2017 11:46:15 AM
* Version：V1.0 
*/
public class DBConnection {
	
	static Connection conn=null;
	private static String username="root";
	private static String password ="Jali6990";
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/swingapp";
	
	/**
	* Function description： try to build a DB connection and return the connection
	* Author：Huimin Liu
	* Create time：May 21, 2017 11:46:31 AM
	* Version：V1.0 
	*/
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			//Class.forName(driver).newInstance();
			conn=DriverManager.getConnection(url, username, password);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;		
	}
	
	/**
	* Function description： close created connection when utilized it 
	* Author：Huimin Liu
	* Create time：May 21, 2017 11:46:34 AM
	* Version：V1.0 
	*/
	public void closeConnection() throws SQLException{
		if(conn!=null){
			try{				
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
