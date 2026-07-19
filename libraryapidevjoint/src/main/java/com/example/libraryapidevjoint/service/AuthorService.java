package com.example.libraryapidevjoint.service;

import com.example.libraryapidevjoint.dto.request.AuthorRequestDto;
import com.example.libraryapidevjoint.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto create(AuthorRequestDto requestDto);
    AuthorResponseDto getById(Long id);
    List<AuthorResponseDto> getAll();
    AuthorResponseDto update(Long id, AuthorRequestDto requestDto);
    void delete(Long id);
}
