package com.example.libraryapidevjoint.mapper;

import com.example.libraryapidevjoint.dto.request.RegisterRequestDto;
import com.example.libraryapidevjoint.dto.response.RegisterResponseDto;
import com.example.libraryapidevjoint.entity.AppUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-25T21:51:33+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public AppUser toEntity(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        AppUser.AppUserBuilder appUser = AppUser.builder();

        appUser.fullName( dto.getFullName() );
        appUser.email( dto.getEmail() );

        return appUser.build();
    }

    @Override
    public RegisterResponseDto toResponse(AppUser user) {
        if ( user == null ) {
            return null;
        }

        RegisterResponseDto.RegisterResponseDtoBuilder registerResponseDto = RegisterResponseDto.builder();

        registerResponseDto.id( user.getId() );
        registerResponseDto.fullName( user.getFullName() );
        registerResponseDto.email( user.getEmail() );

        return registerResponseDto.build();
    }
}
