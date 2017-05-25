package com.jarby.swingApp.entity;

import java.sql.Timestamp;

public class Student {
	
	private int studentid;
	private String studentName;
	private String studentNo;
	private String telephone;
	private String email;
	private String address;
	private Timestamp createtime;
	private Timestamp updatetime;
	
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		if(!(studentid < 0 || studentid == 0)){
			this.studentid = studentid;
		}
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		if(!(studentName == null)){
			this.studentName = studentName;
		}
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		if(!(studentNo == null)){
			this.studentNo = studentNo;
		}		
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
