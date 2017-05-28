package com.huimin.swingApp.entity;

import java.sql.Timestamp;

public class Book {
	private int bookid;
	private String bookName;
	private String author;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String status;
	
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		if(!(bookid < 0 || bookid == 0)){
			this.bookid = bookid;
		}		
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		if(!(bookName == null)){
			this.bookName = bookName;
		}	
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		if(!(author == null)){
			this.author = author;
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
