package com.example.libraryapidevjoint.repository;

import com.example.libraryapidevjoint.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorFullNameContainingIgnoreCase(String fullName);
    List<Book> findByPriceGreaterThanEqual(double price);
    List<Book> findByCategoriesNameIgnoreCase(String categoryName);

    @EntityGraph(attributePaths = {"author", "categories"})
    @Query(value = """
   SELECT DISTINCT b
   FROM Book b
   LEFT JOIN b.author a
   LEFT JOIN b.categories c
   WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
   AND (:author IS NULL OR LOWER(a.fullName) LIKE LOWER(CONCAT('%', :author, '%')))
   AND (:category IS NULL OR LOWER(c.name) = LOWER(:category))
   AND (:minPrice IS NULL OR b.price >= :minPrice)
""",
   countQuery = """
   SELECT COUNT(DISTINCT b)
   FROM Book b
   LEFT JOIN b.author a
   LEFT JOIN b.categories c
   WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
   AND (:author IS NULL OR LOWER(a.fullName) LIKE LOWER(CONCAT('%', :author, '%')))
   AND (:category IS NULL OR LOWER(c.name) = LOWER(:category))
   AND (:minPrice IS NULL OR b.price >= :minPrice)
""")
    Page<Book> filterBooks(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") String category,
            @Param("minPrice") Double minPrice,
            Pageable pageable
    );

    @Override
    @EntityGraph(attributePaths = {"author", "categories"})
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"author", "categories"})
    Page<Book> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"author", "categories"})
    Optional<Book> findById(Long id);
}
