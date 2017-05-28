package com.huimin.swingApp.dao;

import java.util.List;

import com.huimin.swingApp.entity.User;

public interface UserAccountManagementDao {
	
	public User selectUserByUsername(String sql);
	public int executeUpdate(String sql);
	public List<User> query(String sql);
	public User selectUserByUserid(int userid);
}
