package com.jarby.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jarby.swingApp.JDBCUtils.DBConnection;
import com.jarby.swingApp.dao.UserAccountManagementDao;
import com.jarby.swingApp.entity.User;

public class UserAccountManagementDaoImpl implements UserAccountManagementDao {
	private Connection conn;
	private Statement statement;
	
	public User selectUserByUsername(String sql){
		User user = null;
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
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(!(statement==null)){
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return user;
	}
	
	public int executeUpdate(String sql){
	
		int resultSet = 0;
		conn = DBConnection.getConnection();
		try {
			statement = conn.createStatement();
			resultSet = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(!(statement==null)){
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return resultSet;
	}
	
	public List<User> query(String sql){
		List<User> userlist = new ArrayList<User>();
		ResultSet resultSet = null;
		User user = null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(!(statement==null)){
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return userlist;
	}
	
	public User selectUserByUserid(int userid){
		User user = null;
		String sql = "select * from user where 1=1 and userid='"+userid+"'";
		Statement st = null;
		ResultSet rs = null;
		System.out.println(sql);
		conn = DBConnection.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			user = new User();
			while(rs.next()){
				user.setUserid(rs.getInt("userid"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setRoleName(rs.getString("roleName"));
				user.setStudentNo(rs.getString("studentNo"));
				break;
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(!(statement==null)){
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return user;
	}
	
}
