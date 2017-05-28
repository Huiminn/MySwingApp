package com.huimin.swingApp.service;

import java.util.List;

import com.huimin.swingApp.entity.User;

public interface UserLoginService {
	
	public User selectUserByUsernameAndRoleName(String userName,String roleName);
	public User selectUserByStudentNo(String studentNo);
}
