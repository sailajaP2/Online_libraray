package com.vcube.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vcube.library.Entity.User;



@Repository
public interface Userrepo extends JpaRepository<User, Integer>{

	@Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = 'InActive' WHERE u.id = :id")
    void deactivateUser(int id);
	
	 @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.status = 'Active'")
	    User loginUser(String email, String password);
	 
	 @Query("SELECT u FROM User u WHERE u.status = 'InActive'")
	    List<User> findAllInactiveUsers();
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE User u SET u.status = 'Active' WHERE u.id = :id")
	 void activateUser(int id);


}
