package com.jarby.swingApp.service;

import java.util.List;

import com.jarby.swingApp.entity.User;

public interface UserLoginService {
	
	public User selectUserByUsernameAndRoleName(String userName,String roleName);

}
