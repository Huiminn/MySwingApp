package com.huimin.swingApp.service;

import java.util.List;
import java.util.Map;

import com.huimin.swingApp.entity.BorrowRecord;

public interface BorrowRecordService {
	
	public void insertBorrowRecord(int bookid,int studentid);
	public List<Map<String, Object>> findAllBookRecords();
	public void updateBookStatus(int borrowid,String status);
	public BorrowRecord selectBorrowRecordById(int borrowid);
	public List<Map<String, Object>> findBorrowRecordsBySmartWay(String sql);
	public List<BorrowRecord> findBorrowRecordsByStudentId(int studentId);
	public List<Map<String, Object>> findAllBooksInitialStage();
}