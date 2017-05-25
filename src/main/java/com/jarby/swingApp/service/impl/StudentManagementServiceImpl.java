package com.jarby.swingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.jarby.swingApp.dao.StudentManagementDao;
import com.jarby.swingApp.dao.impl.StudentManagementDaoImpl;
import com.jarby.swingApp.entity.Student;
import com.jarby.swingApp.service.StudentManagementService;

/**
* Function description： Editing student, including CRUD methods of create,retrieve,update,delete 
* Author：Huimin Liu
* Create time：May 23, 2017 11:32:34 AM
* Version：V1.0 
*/
public class StudentManagementServiceImpl implements StudentManagementService {

	private StudentManagementDao studentManagerDao;
	
	public StudentManagementServiceImpl() {
		// TODO Auto-generated constructor stub
		studentManagerDao = new StudentManagementDaoImpl();
	}
	
	/**
	* Function description： register a student
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:37 AM
	* Version：V1.0 
	*/
	public void addStudent(Student student){
		String sql = "insert student(student_name,studentNo,telephone,email,address,createtime,updatetime) "
				+ "values('"+student.getStudentName()+"','"+student.getStudentNo()+"','"+student.getTelephone()
				+"','"+student.getEmail()+"','"+student.getAddress()+"','"+student.getCreatetime()+"','"+student.getUpdatetime()+"')";
		try {
			studentManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:40 AM
	* Version：V1.0 
	*/
	public void deleteStudent(int studentid){
		String sql = "delete from student where 1=1 and studentid='"+studentid+"'";
		try {
			studentManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Function description： according to studentid to update student info
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:42 AM
	* Version：V1.0 
	*/
	public void updateStudentById(Student student){
		if(student == null)
			return ;
		String sql = "update student set studentNo='"+student.getStudentNo()
		+"',student_name='"+student.getStudentName()+"',telephone='"+student.getTelephone()
		+"',email='"+student.getEmail()+"',address='"+student.getAddress()
		+"',updatetime='"+student.getUpdatetime()+"'";
		System.out.println("sql:"+sql);
		try {
			studentManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Function description： find all students 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:46 AM
	* Version：V1.0 
	*/
	public List<Student> findAllStudents(){
		List<Student> studentlist = null;
		String sql = "select * from student where 1=1";
		try {
			studentlist = studentManagerDao.Query(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentlist;
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:48 AM
	* Version：V1.0 
	*/
	public List<Student> findStudentByName(String studentName){
		List<Student> studentlist = null;
		return studentlist;
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:51 AM
	* Version：V1.0 
	*/
	public List<Student> findStudentsByStatus(String studentStatus){
		List<Student> studentlist = null;
		return studentlist;
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:54 AM
	* Version：V1.0 
	*/
	public Student findStudentById(int studentid){
		Student student = null;
		student = studentManagerDao.findStudentById(studentid);
		return student;
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 23, 2017 11:32:56 AM
	* Version：V1.0 
	*/
	public List<Student> findStudentsBySmartWay(String sql){
		List<Student> studentlist = null;
		try {
			studentlist = studentManagerDao.Query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentlist;
	}
	
	public Student findStudentByStudentNo(String studentNo){
		Student student = null;
		List<Student> studentlist = null;
		String sql = "select * from student where 1=1 and studentNo='"+studentNo+"'";
		System.out.println(sql);
		try {
			studentlist = studentManagerDao.Query(sql);
			System.out.println("studentlist.size():"+studentlist.size());
			if(studentlist.size()>0){
				student = studentlist.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

}
