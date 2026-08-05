package com.example.libraryapidevjoint.mapper;

import com.example.libraryapidevjoint.dto.request.CategoryRequestDto;
import com.example.libraryapidevjoint.dto.response.CategoryResponseDto;
import com.example.libraryapidevjoint.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toEntity(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto toResponse(Category category);
}
