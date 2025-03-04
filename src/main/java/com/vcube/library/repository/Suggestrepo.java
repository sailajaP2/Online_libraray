package com.vcube.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.library.Entity.Suggestbook;


@Repository
public interface Suggestrepo extends JpaRepository<Suggestbook, Integer>{

}
