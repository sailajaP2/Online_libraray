package com.vcube.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.library.Entity.Suggestbook;
import com.vcube.library.repository.Suggestrepo;



@Service
public class Suggestservice {
    
	@Autowired
	private Suggestrepo repo;
	
	public void addSuggestbook(Suggestbook book) {
		
		repo.save(book);
	}
}
