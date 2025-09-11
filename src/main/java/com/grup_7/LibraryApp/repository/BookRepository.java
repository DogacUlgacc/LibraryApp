package com.grup_7.LibraryApp.repository;

import com.grup_7.LibraryApp.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Books, Integer> {
}
