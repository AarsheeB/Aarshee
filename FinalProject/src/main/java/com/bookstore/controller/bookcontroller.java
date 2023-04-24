package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.entity.book;
import com.bookstore.services.ibookservice;

@Controller
@RequestMapping("bookservice")
public class bookcontroller {
	
	@Autowired
	private ibookservice service;
	
	@GetMapping("books")
	public ResponseEntity<List<book>> getBooks(){
		
		List<book> books = service.getBooks();
		return new ResponseEntity<List<book>>(books, HttpStatus.OK);
		
	}
	
	@GetMapping("books/{id}")
	public ResponseEntity<book> getBook(@PathVariable("id") Integer id){
		book book = service.getBook(id);
		return new ResponseEntity<com.bookstore.entity.book>(book, HttpStatus.OK);
	}
	
	@PostMapping("books")
	public ResponseEntity<book> createBook(@RequestBody book book){
		com.bookstore.entity.book b = service.createBook(book);
		return new ResponseEntity<com.bookstore.entity.book>(b, HttpStatus.OK);
		
	}
	
	@PutMapping("books/{id}")
	public ResponseEntity<book> updateBook(@PathVariable("id") int id, @RequestBody book book){
		
		com.bookstore.entity.book b = service.updateBook(id, book);
		return new ResponseEntity<com.bookstore.entity.book>(b, HttpStatus.OK);
	}
	
	@DeleteMapping("books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") int id){
		boolean isDeleted = service.deleteBook(id);
		if(isDeleted){
			String responseContent = "Book has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting book from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
