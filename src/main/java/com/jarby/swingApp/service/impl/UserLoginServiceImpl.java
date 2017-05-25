package com.jarby.swingApp.service.impl;

import java.util.List;

import com.jarby.swingApp.dao.UserLoginDao;
import com.jarby.swingApp.dao.impl.UserLoginDaoImpl;
import com.jarby.swingApp.entity.User;
import com.jarby.swingApp.service.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService {
	
	private UserLoginDao userLoginDao;
	public UserLoginServiceImpl() {
		// TODO Auto-generated constructor stub
		userLoginDao = new UserLoginDaoImpl();
	}
	
	public User selectUserByUsernameAndRoleName(String userName,String roleName){
		List<User> userlist = null;
		User user = null;
		String sql = "select * from user where 1=1 and userName='"
					+userName+"' and roleName='"+roleName+"'";
		userlist = userLoginDao.query(sql);
		if(userlist.size()>0){
			user = userlist.get(0);
		}
		return user;
	}



}
