package com.jarby.swingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jarby.swingApp.entity.Student;


public interface StudentManagementDao {

	public int execUpdate(String sql) throws SQLException;
	public Student findStudentById(int studentid);
	public List<Student> Query (String sqlString) throws SQLException;
	
}
