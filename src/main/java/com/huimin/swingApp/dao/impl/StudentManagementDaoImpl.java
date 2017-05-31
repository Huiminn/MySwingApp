package com.huimin.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.huimin.swingApp.JDBCUtils.DBConnection;
import com.huimin.swingApp.dao.StudentManagementDao;
import com.huimin.swingApp.entity.Book;
import com.huimin.swingApp.entity.Student;

/**
* Function description：  Editing student, including CRUD methods of create,retrieve,update,delete 
* Author：Huimin Liu
* Create time：May 23, 2017 11:28:43 AM
* Version：V1.0 
*/
public class StudentManagementDaoImpl implements StudentManagementDao {

	DBConnection dbc = null;
	Connection conn = null;
	
	public StudentManagementDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Function description： executing the sql script and implement operations of creating,updating,deleting to student
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:27:56 AM
	* Version：V1.0 
	*/
	public int execUpdate(String sql) throws SQLException{
		int result=0;
		Statement statement=null;	
		conn = DBConnection.getConnection();
		try{
			statement=conn.createStatement();
			result=statement.executeUpdate(sql);			
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
			if(statement!=null){
				statement.close();				
			}
			conn.close();
		}
		return result;		
	}
	/**
	* Function description： search student by studentId 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:28:00 AM
	* Version：V1.0 
	*/
	public Student findStudentById(int studentid){
		
		Student student = new Student();
		String sql = "select * from student where 1=1 and studentid='"+studentid+"'";
		System.out.println("sql from dao: "+sql);
		conn = DBConnection.getConnection();
		Statement st = null;
	    try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				student.setStudentid(rs.getInt("studentid"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentNo(rs.getString("studentNo"));
				student.setTelephone(rs.getString("telephone"));
				student.setEmail(rs.getString("email"));
				student.setAddress(rs.getString("address"));
				student.setCreatetime(rs.getTimestamp("createtime"));
				student.setUpdatetime(rs.getTimestamp("updatetime"));
				/*break;*/
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(!(st == null)){
				try {
					st.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}
	    
		return student;
	}
	/**
	* Function description： retrieving student records from DB according to sql script
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:28:04 AM
	* Version：V1.0 
	*/
	public List<Student> Query (String sqlString) throws SQLException{
		
		List<Student> studentlist = new ArrayList<Student>();
		Statement statement=null;
		ResultSet rs=null;
		conn = DBConnection.getConnection();
		try{
			statement=conn.createStatement();
			rs=statement.executeQuery(sqlString);
			while(rs.next()){
				Student student = new Student();
				student.setStudentid(rs.getInt("studentid"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentNo(rs.getString("studentNo"));
				student.setTelephone(rs.getString("telephone"));
				student.setEmail(rs.getString("email"));
				student.setAddress(rs.getString("address"));
				student.setCreatetime(rs.getTimestamp("createtime"));
				student.setUpdatetime(rs.getTimestamp("updatetime"));
				studentlist.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(statement!=null){
				statement.close();
			}
			if(rs!=null){
				rs.close();
			}
			conn.close();
		}
		return studentlist;
	}
}
