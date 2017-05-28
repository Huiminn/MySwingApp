package com.huimin.swingApp.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.huimin.swingApp.ModelConvert.ModelConvert;
import com.huimin.swingApp.dao.BorrowRecordManagerDao;
import com.huimin.swingApp.dao.impl.BorrowRecordManagerDaoImpl;
import com.huimin.swingApp.entity.BorrowRecord;
import com.huimin.swingApp.service.BorrowRecordService;

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
	public List<Map<String, Object>> findAllBookRecords(){
		List<Map<String, Object>> borrowRecordlist = null;
		/*String sql = "select * from borrow_record where 1=1 and status='"+"borrowed"+"' and is_history_record='"+0+"'";*/
		String sql = "select br.borrowid,b.book_name,b.author,br.borrow_time,br.return_time,s.student_name,s.studentNo,br.`status` from borrow_record br LEFT JOIN book b "+
		"on br.bookid = b.bookId LEFT JOIN student s "+
				"on br.studentid = s.studentid  where 1=1";
		borrowRecordlist = borrowRecordManagerDao.findBookBorrowRecordsWithMapList(sql);
		return borrowRecordlist;
	}
	
	public List<Map<String, Object>> findAllBooksInitialStage(){
		List<Map<String, Object>> borrowRecordlist = null;
		/*String sql = "select * from borrow_record where 1=1 and status='borrowed' and is_history_record='0'";*/
		String sql = "select br.borrowid,b.book_name,b.author,br.borrow_time,br.return_time,s.student_name,s.studentNo,br.`status` from borrow_record br LEFT JOIN book b "+
		"on br.bookid = b.bookId LEFT JOIN student s "+
				"on br.studentid = s.studentid  where 1=1";
		borrowRecordlist = borrowRecordManagerDao.findBookBorrowRecordsWithMapList(sql);
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
	public List<Map<String, Object>> findBorrowRecordsBySmartWay(String sql){
		List<Map<String, Object>> borrowRecordlist = null;
	 	borrowRecordlist = borrowRecordManagerDao.findBookBorrowRecordsWithMapList(sql);
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
	
	public static void main(String[] args){
		List<Map<String, Object>> dataList = null;
		BorrowRecordManagerDao borrowRecordManagerDao = new BorrowRecordManagerDaoImpl();
		String sql = "select * from borrow_record br LEFT JOIN book b "
				+ "on br.borrowid = b.bookId LEFT JOIN student s "
				+ "on br.bookid = s.studentid  where 1=1";
		dataList = borrowRecordManagerDao.findBookBorrowRecordsWithMapList(sql);
		/*{"id","Book Name","Author","Borrow Time","Return Time","Student Name","StudentNo","Status","Operation"};*/
		for(int i=0; i < dataList.size(); i ++){
			System.out.println(dataList.get(i).get("borrowid"));
			System.out.println(dataList.get(i).get("book_name"));
			System.out.println(dataList.get(i).get("borrow_time"));
			System.out.println(dataList.get(i).get("return_time"));
			
		}
	}
}
