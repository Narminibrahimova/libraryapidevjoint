package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.entity.Author;
import com.example.libraryapidevjoint.entity.Book;
import com.example.libraryapidevjoint.entity.Category;
import com.example.libraryapidevjoint.exception.ResourceNotFoundException;
import com.example.libraryapidevjoint.mapper.BookMapper;
import com.example.libraryapidevjoint.repository.AuthorRepository;
import com.example.libraryapidevjoint.repository.BookRepository;
import com.example.libraryapidevjoint.repository.CategoryRepository;
import com.example.libraryapidevjoint.service.BookService;
import lombok.*;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static com.example.libraryapidevjoint.specification.BookSpecification.*;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;


    @Override
    @Transactional
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        List<Category> categories=categoryRepository.findAllById(bookRequestDto.getCategoryIds());
        if (categories.size()!=bookRequestDto.getCategoryIds().size()) {
            throw new ResourceNotFoundException("Some categories not found");
        }
        Book book = bookMapper.toEntity(bookRequestDto);
        book.setAuthor(author);
        book.setCategories(new HashSet<>(categories));
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponseDto> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "books", key = "#id")
    public BookResponseDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));
        return bookMapper.toDto(book);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public BookResponseDto update(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        List<Category> categories=categoryRepository.findAllById(bookRequestDto.getCategoryIds());
        if (categories.size()!=bookRequestDto.getCategoryIds().size()) {
            throw new ResourceNotFoundException("Some categories not found");
        }
        bookMapper.updateEntity(bookRequestDto, book);
        book.setAuthor(author);
        book.setCategories(new HashSet<>(categories));
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponseDto> filterBooks(
            String title,
            String author,
            String category,
            Double minPrice,
            int page,
            int size,
            String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> books = bookRepository.filterBooks(
                title,
                author,
                category,
                minPrice,
                pageable
        );
        return books.map(bookMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponseDto> searchBooks(
            String title,
            String author,
            String category,
            Double minPrice,
            int page,
            int size,
            String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Specification<Book> specification =
                Specification
                        .where(hasTitle(title))
                        .and(hasAuthor(author))
                        .and(hasCategory(category))
                        .and(hasMinimumPrice(minPrice));
        Page<Book> books = bookRepository.findAll(specification, pageable);
        return books.map(bookMapper::toDto);
    }
}
