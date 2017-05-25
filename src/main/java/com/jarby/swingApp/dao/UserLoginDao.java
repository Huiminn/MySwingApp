package com.jarby.swingApp.dao;

import java.util.List;

import com.jarby.swingApp.entity.User;

public interface UserLoginDao {
	
	public int executeUpdate(String sql);
	public List<User> query(String sql);
}
