package com.example.libraryapidevjoint.mapper;

import com.example.libraryapidevjoint.dto.response.BorrowRecordResponseDto;
import com.example.libraryapidevjoint.entity.BorrowRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring" )
public interface BorrowMapper {
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target = "userEmail", source = "user.email")
    BorrowRecordResponseDto toDto(BorrowRecord borrowRecord);

}
