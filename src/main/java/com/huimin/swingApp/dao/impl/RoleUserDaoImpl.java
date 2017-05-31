package com.huimin.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.huimin.swingApp.JDBCUtils.DBConnection;
import com.huimin.swingApp.dao.RoleUserDao;

public class RoleUserDaoImpl implements RoleUserDao {
	
	Connection conn = null;
	Statement statement = null;
	
	public void executeUpdate(String sql){
		conn = DBConnection.getConnection();
		int result = 0;
		
		try {
			statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(!(statement == null)){
				try {
					statement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
