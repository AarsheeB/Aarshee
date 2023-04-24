package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.book;

public interface ibookdao {
	
	List<book> getBooks();
	book getBook(int bookId);
	book createBook(book book);
	book updateBook(int bookId, book book);
	boolean deleteBook(int bookId);

}
