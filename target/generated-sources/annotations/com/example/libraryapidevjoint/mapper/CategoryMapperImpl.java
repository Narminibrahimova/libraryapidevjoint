package com.example.libraryapidevjoint.mapper;

import com.example.libraryapidevjoint.dto.request.CategoryRequestDto;
import com.example.libraryapidevjoint.dto.response.CategoryResponseDto;
import com.example.libraryapidevjoint.entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-06T12:43:51+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequestDto categoryRequestDto) {
        if ( categoryRequestDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryRequestDto.getName() );

        return category.build();
    }

    @Override
    public CategoryResponseDto toResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        categoryResponseDto.setId( category.getId() );
        categoryResponseDto.setName( category.getName() );

        return categoryResponseDto;
    }
}
