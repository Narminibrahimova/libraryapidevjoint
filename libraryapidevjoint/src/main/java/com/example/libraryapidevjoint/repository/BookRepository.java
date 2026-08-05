package com.example.libraryapidevjoint.repository;

import com.example.libraryapidevjoint.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorFullNameContainingIgnoreCase(String fullName);
    List<Book> findByPriceGreaterThanEqual(double price);
    List<Book> findByCategoriesNameIgnoreCase(String categoryName);

    @Query("""
   SELECT DISTINCT b
   FROM Book b
   LEFT JOIN b.author a
   LEFT JOIN b.categories c
   WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
   AND (:author IS NULL OR LOWER(a.fullName) LIKE LOWER(CONCAT('%', :author, '%')))
   AND (:category IS NULL OR LOWER(c.name) = LOWER(:category))
   AND (:minPrice IS NULL OR b.price >= :minPrice)
""")
    List<Book> filterBooks(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") String category,
            @Param("minPrice") Double minPrice
    );
}
