package com.huimin.swingApp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.huimin.swingApp.JDBCUtils.DBConnection;
import com.huimin.swingApp.dao.BookManagementDao;
import com.huimin.swingApp.entity.Book;

/**
* Function description： books operations, including CRUD methods of create,retrieve,update,delete 
* Author：Huimin Liu
* Create time：May 21, 2017 11:44:07 AM
* Version：V1.0 
*/
public class BookManagementDaoImpl implements BookManagementDao {
	
	DBConnection dbc = null;
	Connection conn = null;
	
	public BookManagementDaoImpl() {
		
	}
	
	/**
	* Function description： execute sql script and implement the operations to book table
	* Author：Huimin Liu
	* Create time：May 21, 2017 12:22:54 PM
	* Version：V1.0 
	*/
	public int execUpdate(String sql) throws SQLException{
		int result=0;
		Statement statement=null;	
		conn = DBConnection.getConnection();
		try{
			statement=conn.createStatement();
			result=statement.executeUpdate(sql);			
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
			if(statement!=null){
				statement.close();				
			}
			conn.close();
		}
		return result;		
	}
	
	public Book findBookById(int bookid){
		Book book = new Book();
		String sql = "select * from book where 1=1 and bookId='"+bookid+"'";
		conn = DBConnection.getConnection();
		Statement st = null;
		try {
			 st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				book.setBookid(rs.getInt("bookId"));
				book.setBookName(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setStatus(rs.getString("status"));
				book.setCreateTime(rs.getTimestamp("createtime"));
				book.setUpdateTime(rs.getTimestamp("updatetime"));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(!(st == null)){
				try {
					st.close();
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
	    
		return book;
	}
	/**
	* Function description：search book records according to specific conditions
	* Author：Huimin Liu
	* Create time：May 21, 2017 12:24:10 PM
	* Version：V1.0 
	*/
	public List<Book> Query (String sqlString) throws SQLException{
		List<Book> list=new ArrayList<Book>();
		Statement statement=null;
		ResultSet rs=null;
		conn = DBConnection.getConnection();
		try{
			statement=conn.createStatement();
			rs=statement.executeQuery(sqlString);
			while(rs.next()){
				Book book=new Book();
				book.setBookid(rs.getInt("bookid"));
				book.setBookName(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setStatus(rs.getString("status"));
				book.setCreateTime(rs.getTimestamp("createtime"));
				book.setUpdateTime(rs.getTimestamp("updatetime"));
				list.add(book);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(statement!=null){
				statement.close();
			}
			if(rs!=null){
				rs.close();
			}
			conn.close();
		}
		return list;
	}
	
	public static void main(String[] args){
		BookManagementDaoImpl bmdi = new BookManagementDaoImpl();
		Book book = null;
	/*	List<Book> booklist = null;
		String sql="select * from book where 1=1 and status='"+"In"+"'";
		try {
			booklist = bmdi.Query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("size: "+booklist.size());*/
		book = bmdi.findBookById(7);
	}
	
}
