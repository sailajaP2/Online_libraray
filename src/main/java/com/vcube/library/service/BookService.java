package com.vcube.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.library.Entity.Books;
import com.vcube.library.repository.Bookrepo;



@Service
public class BookService {

	@Autowired
	private Bookrepo repo;
	public void saveBook(Books book) {
		 repo.save(book);
		
	}
	public List<Books> getAll(){
		List<Books> all = repo.findActiveBooks();
		return all;
	}
	public Books getBookById(int id) {
		return repo.findById(id).get();
	}
	public void deletebyId(int id) {
		repo.deactivateBook(id);
	}
	public Books getBookbyId(int id) {
		return repo.findById(id).get();
	}
	public List<Books> getInactiveBooks() {
        return repo.findAllInactiveBooks();
    }
	 public void activateBook(int id) {
	        repo.activateBook(id);
	    }
	 public String deleteBook(int bookId) {
	        int usageCount = repo.countUsersUsingBook(bookId);

	        if (usageCount > 0) {
	            return "This book is being used by a user and cannot be deleted.";
	        } else {
	            repo.deleteBookById(bookId);
	            return "Book deleted successfully.";
	        }
	    }
}
