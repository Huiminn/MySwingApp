package com.jarby.swingApp.dao;

import java.util.List;

import com.jarby.swingApp.entity.User;

public interface UserAccountManagementDao {
	
	public User selectUserByUsername(String sql);
	public int executeUpdate(String sql);
	public List<User> query(String sql);
	public User selectUserByUserid(int userid);
}
