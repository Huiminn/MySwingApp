package com.jarby.swingApp.service;

import java.util.List;

import com.jarby.swingApp.entity.User;

public interface UserAccountManagementService {
	public void insertUser(User user);
	public User selectUserByUsername(String userName);
	public List<User> selectAllUsers();
	public void deleteUser(int userid);
	public User selectUserByUserid(int userid);
	public void updateRoleNameByUserid(String roleName,int userid);
	public User selectUserByUsernameAndRoleName(String userName,String roleName);
}
