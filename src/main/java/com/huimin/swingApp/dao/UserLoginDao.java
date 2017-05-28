package com.huimin.swingApp.dao;

import java.util.List;

import com.huimin.swingApp.entity.User;

public interface UserLoginDao {
	
	public int executeUpdate(String sql);
	public List<User> query(String sql);
}
