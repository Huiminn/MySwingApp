package com.jarby.swingApp.service;

import java.util.List;

import com.jarby.swingApp.entity.Book;

public interface BookManagementService  {

	public void addBook(Book book);
	public void deleteBook(int bookid);
	public void updateBookById(Book book);
	public List<Book> findAllBooks();
	public List<Book> findBooksByName(String bookName);
	public List<Book> findBooksByStatus(String bookStatus);
	public Book findBookById(int bookid);
	public List<Book> findBooksBySmartWay(String sql);
	public void updateBookStatusOutByBookid(int bookid);
	public void updateBookStatus(int bookid,String status);
	
}
