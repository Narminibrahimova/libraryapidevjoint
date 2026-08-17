package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.dto.request.AuthorRequestDto;
import com.example.libraryapidevjoint.dto.response.AuthorResponseDto;
import com.example.libraryapidevjoint.entity.Author;
import com.example.libraryapidevjoint.exception.ResourceNotFoundException;
import com.example.libraryapidevjoint.repository.AuthorRepository;
import com.example.libraryapidevjoint.service.AuthorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl  implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    @Transactional
    public AuthorResponseDto create(AuthorRequestDto request) {
        Author author = new Author();
        author.setFullName(request.getFullName());
        Author savedAuthor = authorRepository.save(author);
        AuthorResponseDto response = new AuthorResponseDto();
        response.setId(savedAuthor.getId());
        response.setFullName(savedAuthor.getFullName());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponseDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponseDto> responses = new ArrayList<>();
        for (Author author : authors) {
            AuthorResponseDto response = new AuthorResponseDto();
            response.setId(author.getId());
            response.setFullName(author.getFullName());
            responses.add(response);
        }
        return responses;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponseDto getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        AuthorResponseDto response = new AuthorResponseDto();
        response.setId(author.getId());
        response.setFullName(author.getFullName());
        return response;
    }

    @Override
    @Transactional
    public AuthorResponseDto update(Long id, AuthorRequestDto request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        author.setFullName(request.getFullName());
        Author updatedAuthor = authorRepository.save(author);
        AuthorResponseDto response = new AuthorResponseDto();
        response.setId(updatedAuthor.getId());
        response.setFullName(updatedAuthor.getFullName());
        return response;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        authorRepository.delete(author);
    }
}
