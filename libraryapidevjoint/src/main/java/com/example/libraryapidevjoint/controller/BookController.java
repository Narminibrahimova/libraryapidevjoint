package com.example.libraryapidevjoint.controller;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Management", description = "APIs for managing, searching, and filtering books")
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Operation(
            summary = "Create a new book",
            description = "Creates a new book with an author and categories"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Author or category not found")
    })
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(
            @Valid @RequestBody BookRequestDto request) {
        BookResponseDto response = bookService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }




    @Operation(
            summary = "Get all books",
            description = "Returns paginated and sorted books"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Books retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(bookService.getAll(page, size, sortBy));
    }





    @Operation(
            summary = "Get book by ID",
            description = "Returns a book by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(
            @PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }





    @Operation(
            summary = "Update a book",
            description = "Updates an existing book"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Book, author or category not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDto request) {

        return ResponseEntity.ok(bookService.update(id, request));
    }




    @Operation(
            summary = "Delete a book",
            description = "Deletes a book by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }




    @Operation(
            summary = "Filter books",
            description = "Returns paginated filtered books by title, author, category, or minPrice"
    )
    @GetMapping("/filter")
    public ResponseEntity<Page<BookResponseDto>> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(
                bookService.filterBooks(
                        title,
                        author,
                        category,
                        minPrice,
                        page,
                        size,
                        sortBy
                )
        );
    }

    @Operation(
            summary = "Search books",
            description = "Returns paginated searched books using JPA Specification"
    )
    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDto>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(
                bookService.searchBooks(
                        title,
                        author,
                        category,
                        minPrice,
                        page,
                        size,
                        sortBy
                )
        );
    }
}
