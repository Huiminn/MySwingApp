package com.huimin.swingApp.service.impl;

import java.util.List;

import com.huimin.swingApp.dao.UserLoginDao;
import com.huimin.swingApp.dao.impl.UserLoginDaoImpl;
import com.huimin.swingApp.entity.User;
import com.huimin.swingApp.service.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService {
	
	private UserLoginDao userLoginDao;
	public UserLoginServiceImpl() {
		
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

	/**
	* Function description： search user by from user table, according to student No.
	* Author：Huimin Liu
	* Create time：May 26, 2017 12:00:25 PM
	* Version：V1.0 
	*/
	public User selectUserByStudentNo(String studentNo){
		List<User> userlist = null;
		User user = null;
		String sql = "select * from user where 1=1 and studentNo='"+studentNo+"'";
		userlist = userLoginDao.query(sql);
		if(userlist.size() > 0)
			user = userlist.get(0);
		return user;
	}

}
