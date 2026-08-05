package com.example.libraryapidevjoint.specification;

import com.example.libraryapidevjoint.entity.Book;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> hasTitle(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, cb) -> {
            if (author == null || author.isBlank()) {
                return cb.conjunction();
            }
            Join<Object, Object> authorJoin =
                    root.join("author", JoinType.LEFT);
            return cb.like(
                    cb.lower(authorJoin.get("fullName")),
                    "%" + author.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Book> hasCategory(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isBlank()) {
                return cb.conjunction();
            }
            Join<Object, Object> categoryJoin =
                    root.join("categories", JoinType.LEFT);
            return cb.equal(
                    cb.lower(categoryJoin.get("name")),
                    category.toLowerCase()
            );
        };
    }

    public static Specification<Book> hasMinimumPrice(Double minPrice) {
        return (root, query, cb) -> {
            if (minPrice == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(
                    root.get("price"),
                    minPrice
            );
        };
    }
}
