package com.example.libraryapidevjoint.repository;

import com.example.libraryapidevjoint.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
