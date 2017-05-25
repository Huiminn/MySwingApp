package com.jarby.swingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jarby.swingApp.entity.Book;

public interface BookManagementDao  {
	
	public int execUpdate(String sql) throws SQLException;
	public Book findBookById(int bookid);
	public List<Book> Query (String sqlString) throws SQLException;
	/*public List<Book> findBooksByStatus(String bookStatus);*/
	/*public void addBook(Book book);
	public void deleteBook(int bookid);
	public void updateBookById(Book book);
	public List<Book> findAllBooks();
	public List<Book> findBooksByName(String bookName);
	public List<Book> findBooksByStatus(String bookStatus);
	public Book findBookById(int bookid);*/
	
}
