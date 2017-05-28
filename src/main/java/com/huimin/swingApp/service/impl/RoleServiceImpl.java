package com.huimin.swingApp.service.impl;

import com.huimin.swingApp.dao.RoleDao;
import com.huimin.swingApp.dao.impl.RoleDaoImpl;
import com.huimin.swingApp.entity.Role;
import com.huimin.swingApp.service.RoleService;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	
	public RoleServiceImpl() {
		// TODO Auto-generated constructor stub
		roleDao = new RoleDaoImpl();
	}
	public Role selectRoleByRoleName(String roleName){
		Role role = null;
		String sql = "select * from role where 1=1 and roleName='"+roleName+"'";
		role = roleDao.selectRoleByRoleName(sql);
		return role;
	}
}
