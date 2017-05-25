package com.jarby.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jarby.swingApp.JDBCUtils.DBConnection;
import com.jarby.swingApp.dao.BorrowRecordManagerDao;
import com.jarby.swingApp.entity.BorrowRecord;

public class BorrowRecordManagerDaoImpl implements BorrowRecordManagerDao {
	Connection conn = null;
	Statement statement = null;
	
	public BorrowRecordManagerDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public int executeUpdate(String sql){
		int result = 0;
		conn = DBConnection.getConnection();
		try {
			statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(!(statement == null)){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<BorrowRecord> query(String sql){
		List<BorrowRecord> borrowRecordslist = new ArrayList<BorrowRecord>();
		ResultSet resultSet = null;
		conn = DBConnection.getConnection();
		BorrowRecord borrowRecord = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				borrowRecord = new BorrowRecord();
				borrowRecord.setBookid(resultSet.getInt("bookid"));
				borrowRecord.setBorrowid(resultSet.getInt("borrowid"));
				borrowRecord.setStudentid(resultSet.getInt("studentid"));
				borrowRecord.setStatus(resultSet.getString("status"));
				borrowRecord.setBorrowTime(resultSet.getTimestamp("borrow_time"));
				borrowRecord.setReturnTime(resultSet.getTimestamp("return_time"));
				borrowRecordslist.add(borrowRecord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(!(statement == null)){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return borrowRecordslist;
	}
}
