package com.example.libraryapidevjoint.mapper;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.entity.Author;
import com.example.libraryapidevjoint.entity.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-25T21:51:33+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(BookRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.title( dto.getTitle() );
        book.price( dto.getPrice() );

        return book.build();
    }

    @Override
    public BookResponseDto toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponseDto.BookResponseDtoBuilder bookResponseDto = BookResponseDto.builder();

        bookResponseDto.authorName( bookAuthorFullName( book ) );
        bookResponseDto.id( book.getId() );
        bookResponseDto.title( book.getTitle() );
        bookResponseDto.price( book.getPrice() );

        return bookResponseDto.build();
    }

    @Override
    public void updateEntity(BookRequestDto dto, Book book) {
        if ( dto == null ) {
            return;
        }

        book.setTitle( dto.getTitle() );
        book.setPrice( dto.getPrice() );
    }

    private String bookAuthorFullName(Book book) {
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        return author.getFullName();
    }
}
