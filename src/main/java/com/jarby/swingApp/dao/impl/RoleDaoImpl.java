package com.jarby.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jarby.swingApp.JDBCUtils.DBConnection;
import com.jarby.swingApp.dao.RoleDao;
import com.jarby.swingApp.entity.Role;

public class RoleDaoImpl implements RoleDao {

	Connection conn = null;
	Statement statement = null;
	
	public Role selectRoleByRoleName(String sql){
		Role role = null;
		conn = DBConnection.getConnection();
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				role = new Role();
				role.setRoleid(resultSet.getInt("roleid"));
				role.setRoleName(resultSet.getString("roleName"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(!(statement == null)){
				try {
					statement.close();					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return role;
	}
}
