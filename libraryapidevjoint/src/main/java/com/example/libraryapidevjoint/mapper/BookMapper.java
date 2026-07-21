package com.example.libraryapidevjoint.mapper;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", ignore = true)
    Book toEntity(BookRequestDto dto);

    @Mapping(source = "author.fullName",
            target = "authorName")
    BookResponseDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    void updateEntity(BookRequestDto dto, @MappingTarget Book book);
}
