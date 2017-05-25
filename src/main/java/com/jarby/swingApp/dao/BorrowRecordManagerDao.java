package com.jarby.swingApp.dao;

import java.util.List;

import com.jarby.swingApp.entity.BorrowRecord;

public interface BorrowRecordManagerDao {
	
	public int executeUpdate(String sql);
	public List<BorrowRecord> query(String sql);
}
