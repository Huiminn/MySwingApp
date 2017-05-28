package com.huimin.swingApp.service;

import java.util.List;

import com.huimin.swingApp.entity.Student;



public interface StudentManagementService {
	public void addStudent(Student student);
	public void deleteStudent(int studentid);
	public void updateStudentById(Student student);
	public List<Student> findAllStudents();
	public List<Student> findStudentByName(String studentName);
	public List<Student> findStudentsByStatus(String studentStatus);
	public Student findStudentById(int studentid);
	public List<Student> findStudentsBySmartWay(String sql);
	public Student findStudentByStudentNo(String studentNo);
}
