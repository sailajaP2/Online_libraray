package com.vcube.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.library.Entity.User;
import com.vcube.library.repository.Userrepo;


@Service
public class Userservice {

	@Autowired
	private Userrepo repo;
	public User login(String email,String password) {
		
		User res = repo.loginUser(email, password);
		return res;
	}
	public User saveUser(User user) {
		User res = repo.save(user);
		return res;
	}
	public List<User> getallUsers(){
		
		List<User> all = repo.findAll();
		return all;
	}
	public void deleteuserByid(int id) {
		repo.deactivateUser(id);
	}
	 public List<User> getInactiveUsers() {
	        return repo.findAllInactiveUsers();
	    }
	public void activetheinactiveuser(int id) {
		repo.activateUser(id);
		
	}
	public void deleteuser(int id) {
		repo.deleteById(id);
	}
	public User getUserByid(int id) {
		Optional<User> user = repo.findById(id);
		return user.get();
		
	}
}
