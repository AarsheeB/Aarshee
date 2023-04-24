package com.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.ibookdao;
import com.bookstore.entity.book;

@Service
public class bookservice implements ibookservice {
	
	@Autowired
	private ibookdao dao;

	@Override
	public List<book> getBooks() {
		return dao.getBooks();
	}

	@Override
	public book createBook(book book) {
		return dao.createBook(book);
	}

	@Override
	public book updateBook(int bookId, book book) {
		return dao.updateBook(bookId, book);
	}

	@Override
	public book getBook(int bookId) {
		return dao.getBook(bookId);
	}

	@Override
	public boolean deleteBook(int bookId) {
		return dao.deleteBook(bookId);
	}

}
