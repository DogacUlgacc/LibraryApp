package com.grup_7.LibraryApp.repository;

import com.grup_7.LibraryApp.entity.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}