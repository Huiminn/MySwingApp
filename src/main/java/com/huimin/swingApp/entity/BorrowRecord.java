package com.huimin.swingApp.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class BorrowRecord {
	private int borrowid;
	private int studentid;
	private int bookid;
	private String status;
	private Timestamp borrowTime;
	private Timestamp returnTime;
	private boolean isHistoryRecord;
	private List<Book> booklist;
	private List<Student> studentlist;
	public int getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(int borrowid) {
		if(!(borrowid < 0 || borrowid == 0)){
			this.borrowid = borrowid;
		}
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		if(!(studentid < 0 || studentid == 0)){
			this.studentid = studentid;
		}		
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		if(!(bookid < 0 || bookid == 0)){
			this.bookid = bookid;
		}	
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(!(status == null)){
			this.status = status;
		}		
	}
	public Timestamp getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Timestamp borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Timestamp getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}
	public boolean isHistoryRecord() {
		return isHistoryRecord;
	}
	public void setHistoryRecord(boolean isHistoryRecord) {
		this.isHistoryRecord = isHistoryRecord;
	}
	public List<Book> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}
	public List<Student> getStudentlist() {
		return studentlist;
	}
	public void setStudentlist(List<Student> studentlist) {
		this.studentlist = studentlist;
	}
	
	

}
