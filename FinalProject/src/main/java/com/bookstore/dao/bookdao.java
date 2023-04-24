package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.book;

@Transactional
@Repository
public class bookdao implements ibookdao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all books available in database and return it as List<Book>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<book> getBooks() {
		
		String hql = "FROM Book as atcl ORDER BY atcl.id";
		return (List<book>) entityManager.createQuery(hql).getResultList();
	}

	/**
	 * This method is responsible to get a particular Book detail by given book id 
	 */
	@Override
	public book getBook(int bookId) {
		
		return entityManager.find(book.class, bookId);
	}

	/**
	 * This method is responsible to create new book in database
	 */
	@Override
	public book createBook(book book) {
		entityManager.persist(book);
		com.bookstore.entity.book b = getLastInsertedBook();
		return b;
	}

	/**
	 * This method is responsible to update book detail in database
	 */
	@Override
	public book updateBook(int bookId, book book) {
		
		//First We are taking Book detail from database by given book id and 
		// then updating detail with provided book object
		com.bookstore.entity.book bookFromDB = getBook(bookId);
		bookFromDB.setName(book.getName());
		bookFromDB.setAuthor(book.getAuthor());
		bookFromDB.setCategory(book.getCategory());
		bookFromDB.setPublication(book.getPublication());
		bookFromDB.setPages(book.getPages());
		bookFromDB.setPrice(book.getPrice());
		
		entityManager.flush();
		
		//again i am taking updated result of book and returning the book object
		com.bookstore.entity.book updatedBook = getBook(bookId);
		
		return updatedBook;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteBook(int bookId) {
		book book = getBook(bookId);
		entityManager.remove(book);
		
		//we are checking here that whether entityManager contains earlier deleted book or not
		// if contains then book is not deleted from DB that's why returning false;
		boolean status = entityManager.contains(book);
		if(status){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Book class
	 * @return book
	 */
	private book getLastInsertedBook(){
		String hql = "from Book order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		book book = (com.bookstore.entity.book)query.getSingleResult();
		return book;
	}

}
