package com.bookstore.services;

import java.util.List;

import com.bookstore.entity.book;

public interface ibookservice {
	
	List<book> getBooks();
	book createBook(book book);
	book updateBook(int bookId, book book);
	book getBook(int bookId);
	boolean deleteBook(int bookId);

}
