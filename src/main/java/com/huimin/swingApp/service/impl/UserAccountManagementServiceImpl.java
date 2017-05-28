package com.huimin.swingApp.service.impl;

import java.util.List;

import com.huimin.swingApp.dao.UserAccountManagementDao;
import com.huimin.swingApp.dao.impl.UserAccountManagementDaoImpl;
import com.huimin.swingApp.entity.User;
import com.huimin.swingApp.service.RoleUserService;
import com.huimin.swingApp.service.UserAccountManagementService;

public class UserAccountManagementServiceImpl implements UserAccountManagementService {

	private UserAccountManagementDao userAccountManagementDao;
	private RoleUserService roleUserService;
	public UserAccountManagementServiceImpl() {
		userAccountManagementDao = new UserAccountManagementDaoImpl();
		roleUserService = new RoleUserServiceImpl();
	}
	public void insertUser(User user){
		String sql = "insert user(userName,password,roleName,studentNo) values('"
				+user.getUserName()+"','"+user.getPassword()+"','"+user.getRoleName()+"','"+user.getStudentNo()+"'"+")";
		userAccountManagementDao.executeUpdate(sql);
	}
	public User selectUserByUsername(String userName){
		User user = null;
		String sql = "select * from user where 1=1 and userName like '%"+userName+"%'";
		user = userAccountManagementDao.selectUserByUsername(sql);
		return user;
	}
	
	/**
	* Function description： search all users
	* Author：Huimin Liu
	* Create time：May 23, 2017 10:17:01 PM
	* Version：V1.0 
	*/
	public List<User> selectAllUsers(){
		List<User> userlist = null;
		String sql = "select * from user where 1=1";
		userlist = userAccountManagementDao.query(sql);
		return userlist;
	}
	
	/**
	* Function description： delete user record according to userid
	* Author：Huimin Liu
	* Create time：May 23, 2017 10:16:36 PM
	* Version：V1.0 
	*/
	public void deleteUser(int userid){
		String sql = "delete from user where 1=1 and userid='"+userid+"'";
		userAccountManagementDao.executeUpdate(sql);
		roleUserService.deleteRoleUserRecord(userid);
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:26:53 PM
	* Version：V1.0 
	*/
	public User selectUserByUserid(int userid){
		User user = null;
		user = userAccountManagementDao.selectUserByUserid(userid);
		return user;
	}
	
	/**
	* Function description： update user record by roleName
	* Author：Huimin Liu
	* Create time：May 24, 2017 12:01:20 AM
	* Version：V1.0 
	*/
	public void updateRoleNameByUserid(String roleName,int userid){
		String sql = "update user set roleName='"+roleName+"' where 1=1 and userid='"+userid+"'";
		userAccountManagementDao.executeUpdate(sql);
	}
	
	public User selectUserByUsernameAndRoleName(String userName,String roleName){
		List<User> userlist = null;
		User user = null;
		String sql = "select * from user where 1=1 and userName='"+userName+"' and roleName='"+roleName+"'";
		System.out.println(sql);
		userlist = userAccountManagementDao.query(sql);
		if(userlist.size() > 0)
			user = userlist.get(0);
		return user;
	}
	
	
}
