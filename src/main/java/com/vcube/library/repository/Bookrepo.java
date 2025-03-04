package com.vcube.library.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vcube.library.Entity.Books;


@Repository
public interface Bookrepo extends JpaRepository<Books, Integer>{

	@Modifying
    @Transactional
    @Query("UPDATE Books b SET b.status = 'InActive' WHERE b.id = :id")
    void deactivateBook(int id);

    // Query to fetch only active books
    @Query("SELECT b FROM Books b WHERE b.status = 'Active'")
    List<Books> findActiveBooks();
    
    @Query("SELECT b FROM Books b WHERE b.status = 'InActive'")
    List<Books> findAllInactiveBooks();
    
    @Modifying
    @Transactional
    @Query("UPDATE Books b SET b.status = 'Active' WHERE b.id = :id")
    void activateBook(int id);
    
    @Query("SELECT COUNT(u) FROM User u JOIN u.listofbooks b WHERE b.id = :bookId")
    int countUsersUsingBook(int bookId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Books b WHERE b.id = :id")
    void deleteBookById(int id);

}
