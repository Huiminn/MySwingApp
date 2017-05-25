package com.jarby.swingApp.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.jarby.swingApp.dao.BorrowRecordManagerDao;
import com.jarby.swingApp.dao.impl.BorrowRecordManagerDaoImpl;
import com.jarby.swingApp.entity.BorrowRecord;
import com.jarby.swingApp.service.BorrowRecordService;

public class BorrowRecordServiceImpl implements BorrowRecordService {
	
	private BorrowRecordManagerDao borrowRecordManagerDao;
	public BorrowRecordServiceImpl() {
		borrowRecordManagerDao = new BorrowRecordManagerDaoImpl();
	}
	public void insertBorrowRecord(int bookid,int studentid){
		BorrowRecord borrowRecord = null;
		String sql = "insert borrow_record(studentid,bookid,borrow_time,status,is_history_record) values('"
		+studentid+"','"+bookid+"','"+new Timestamp(System.currentTimeMillis())
		+"','"+"borrowed"+"','"+0+"')";
		System.out.println(sql);
		borrowRecordManagerDao.executeUpdate(sql);
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 24, 2017 5:40:03 PM
	* Version：V1.0 
	*/
	public List<BorrowRecord> findAllBookRecords(){
		List<BorrowRecord> borrowRecordlist = null;
		String sql = "select * from borrow_record where 1=1 and status='"+"borrowed"+"' and is_history_record='"+0+"'";
		borrowRecordlist = borrowRecordManagerDao.query(sql);
		return borrowRecordlist;
	}
	
	public List<BorrowRecord> findAllBooksInitialStage(){
		List<BorrowRecord> borrowRecordlist = null;
		String sql = "select * from borrow_record where 1=1 and status='borrowed' and is_history_record='0'";
		borrowRecordlist = borrowRecordManagerDao.query(sql);
		return borrowRecordlist;
	}
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 24, 2017 5:39:59 PM
	* Version：V1.0 
	*/
	public void updateBookStatus(int borrowid,String status){
		String sql = "update borrow_record set status='"+status+"', return_time='"+new Timestamp(System.currentTimeMillis())+"', is_history_record='"+1+"' where borrowid='"+borrowid+"'";
		System.out.println(sql);
		borrowRecordManagerDao.executeUpdate(sql);
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 24, 2017 5:39:55 PM
	* Version：V1.0 
	*/
	public BorrowRecord selectBorrowRecordById(int borrowid){
		BorrowRecord borrowRecord = null;
		List<BorrowRecord> borrowRecordlist = null;
		String sql = "select * from borrow_record where borrowid='"+borrowid+"'";
		borrowRecordlist = borrowRecordManagerDao.query(sql);
		if(borrowRecordlist.size() > 0 ){
			borrowRecord = borrowRecordlist.get(0);
		}
		return borrowRecord;
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 24, 2017 5:39:49 PM
	* Version：V1.0 
	*/
	public List<BorrowRecord> findBorrowRecordsBySmartWay(String sql){
		List<BorrowRecord> borrowRecordlist = null;
	 	borrowRecordlist = borrowRecordManagerDao.query(sql);
		return borrowRecordlist;
	}
	
	/**
	* Function description： 
	* Author：Huimin Liu
	* Create time：May 24, 2017 6:16:37 PM
	* Version：V1.0 
	*/
	public List<BorrowRecord> findBorrowRecordsByStudentId(int studentId){
		List<BorrowRecord> borrowRecordlist = null;
		String sql = "select * from borrow_record where 1=1 and studentid='"+studentId+"'";
		borrowRecordlist = borrowRecordManagerDao.query(sql);
		return borrowRecordlist;
	}
}
