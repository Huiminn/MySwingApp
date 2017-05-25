package com.jarby.swingApp.service.impl;

import com.jarby.swingApp.dao.RoleUserDao;
import com.jarby.swingApp.dao.impl.RoleUserDaoImpl;
import com.jarby.swingApp.entity.RoleUser;
import com.jarby.swingApp.service.RoleUserService;

public class RoleUserServiceImpl implements RoleUserService {
	
	private RoleUserDao roleUserDao;
	public RoleUserServiceImpl() {
		roleUserDao = new RoleUserDaoImpl();
		
	}
	public void insertRoleUserRecord(RoleUser roleUser){
		String sql = "insert role_user(roleid,userid) values('"+roleUser.getRoleid()+"','"+roleUser.getUserid()+"')";
		roleUserDao.executeUpdate(sql);
		System.out.println(sql);
	}
	
	public void deleteRoleUserRecord(int userid){
		String sql = "delete from role_user where userid='"+userid+"'";
		roleUserDao.executeUpdate(sql);
		System.out.println(sql);
	}
}
