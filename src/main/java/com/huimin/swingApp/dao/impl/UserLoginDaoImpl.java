package com.huimin.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.omg.CORBA.COMM_FAILURE;

import com.huimin.swingApp.JDBCUtils.DBConnection;
import com.huimin.swingApp.dao.UserLoginDao;
import com.huimin.swingApp.entity.User;

public class UserLoginDaoImpl implements UserLoginDao {
	
	private Connection conn;
	private Statement statement;
	
	
	public UserLoginDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public int executeUpdate(String sql){
		int result = 0;
		Statement statement = null;
		conn = DBConnection.getConnection();
		try {
			statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(!(statement==null)){
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
		return result;
	}
	public List<User> query(String sql){
		List<User> userlist = new ArrayList<User>();
		User user = null;
		Statement statement = null;
		ResultSet resultSet = null;
		conn = DBConnection.getConnection();
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				user = new User();
				user.setUserid(resultSet.getInt("userid"));
				user.setUserName(resultSet.getString("userName"));
				user.setPassword(resultSet.getString("password"));
				user.setRoleName(resultSet.getString("roleName"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(!(statement==null)){
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
	
		return userlist;
	}
	
}
