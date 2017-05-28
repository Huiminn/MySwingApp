package com.huimin.swingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.huimin.swingApp.dao.BookManagementDao;
import com.huimin.swingApp.dao.impl.BookManagementDaoImpl;
import com.huimin.swingApp.entity.Book;
import com.huimin.swingApp.service.BookManagementService;

public class BookManagementServiceImpl implements BookManagementService{
	
	private BookManagementDao bookManagerDao = null;
	
	public BookManagementServiceImpl() {
		bookManagerDao = new BookManagementDaoImpl();
	}
	
	public void addBook(Book book){
		if(book == null){
			//add exceptions here;
			return ;
		}
		String sql = "insert book(book_name,author,createtime,updatetime,status) "
				+ "values('"+book.getBookName()+"','"+book.getAuthor()+"','"+book.getCreateTime()+"','"+book.getUpdateTime()+"','"+book.getStatus()+"')";
		System.out.println("sql commands: "+sql);
		try {
			bookManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int bookid){
		if(bookid < 0 || bookid == 0)
			return ;
		String sql = "delete from book where 1=1 and bookId='"+bookid+"'";
		try {
			bookManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBookById(Book book){
		if(book == null){
			return ;
		}
		String sql = "update book set book_name='"+book.getBookName()+"'"+",author='"+book.getAuthor()+"'"+
		",updatetime='"+book.getUpdateTime()+"'"+",status='"+book.getStatus()+"'"+"where 1=1 and bookId='"+book.getBookid()+"'"; 
		System.out.println(sql);
		try {
			bookManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public List<Book> findAllBooks(){
		List<Book> booklist = null;
		String sqlString = "select * from book where 1=1";
		try {
			booklist = bookManagerDao.Query(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booklist;
	}
	
	public List<Book> findBooksByName(String bookName){
		List<Book> booklist = null;
		return booklist;
	}
	
	public List<Book> findBooksBySmartWay(String sql){
		List<Book> booklist = null;
		try {
			booklist = bookManagerDao.Query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booklist;
	}
	public List<Book> findBooksByStatus(String bookStatus){
		List<Book> booklist = null;
		if(bookStatus == null || "".equals(bookStatus)){
			//throw exception
		}
		System.out.println("bookStatus:"+bookStatus);
		String sql = "select * from book where 1=1 and status='"+bookStatus+"'";
		System.out.println(sql);
		try {
			booklist = bookManagerDao.Query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booklist;
	}
	
	public Book findBookById(int bookid){
		Book book = null;
		if(bookid < 0 || bookid == 0)
			return book;
		book = bookManagerDao.findBookById(bookid);
		return book;
	}
	public static void main(String[] args){
		BookManagementServiceImpl bs = new BookManagementServiceImpl();
		Book book = null;
		/*List<Book> booklist = null;
		booklist = bs.findBooksByStatus("In");
		int i=0;
		for(Book book:booklist){
			System.out.println("***********"+(++i)+"*********");
			System.out.println("Book name:"+book.getBookName());
			System.out.println("Author: "+book.getAuthor());
			System.out.println("Status: "+book.getStatus());
		}
		System.out.println("book size from bookServiceImpl: "+booklist.size());*/
		book = bs.findBookById(7);
		System.out.println("Book name:"+book.getBookName());
		System.out.println("Author: "+book.getAuthor());
		System.out.println("Status: "+book.getStatus());
	}
	
	public void updateBookStatusOutByBookid(int bookid){
		String sql = "update book set status='"+"Out"+"' where 1=1 and bookid='"+bookid+"'";
		try {
			bookManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBookStatus(int bookid,String status){
		String sql = "update book set status='"+status+"' where bookid='"+bookid+"'";
		System.out.println(sql);
		try {
			bookManagerDao.execUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
