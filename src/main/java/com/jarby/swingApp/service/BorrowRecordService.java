package com.jarby.swingApp.service;

import java.util.List;

import com.jarby.swingApp.entity.BorrowRecord;

public interface BorrowRecordService {
	
	public void insertBorrowRecord(int bookid,int studentid);
	public List<BorrowRecord> findAllBookRecords();
	public void updateBookStatus(int borrowid,String status);
	public BorrowRecord selectBorrowRecordById(int borrowid);
	public List<BorrowRecord> findBorrowRecordsBySmartWay(String sql);
	public List<BorrowRecord> findBorrowRecordsByStudentId(int studentId);
	public List<BorrowRecord> findAllBooksInitialStage();
}