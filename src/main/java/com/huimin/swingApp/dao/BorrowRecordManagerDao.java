package com.huimin.swingApp.dao;

import java.util.List;
import java.util.Map;

import com.huimin.swingApp.entity.BorrowRecord;

public interface BorrowRecordManagerDao {
	
	public int executeUpdate(String sql);
	public List<BorrowRecord> query(String sql);
	public List<Map<String, Object>> findBookBorrowRecordsWithMapList(String sql);
}
