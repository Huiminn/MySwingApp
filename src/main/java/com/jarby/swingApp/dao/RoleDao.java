package com.jarby.swingApp.dao;

import java.util.List;

import com.jarby.swingApp.entity.Role;

public interface RoleDao {
	
	public Role selectRoleByRoleName(String sql);
}
