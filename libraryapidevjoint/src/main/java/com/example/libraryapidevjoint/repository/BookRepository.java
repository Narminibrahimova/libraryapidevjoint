package com.example.libraryapidevjoint.repository;

import com.example.libraryapidevjoint.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
