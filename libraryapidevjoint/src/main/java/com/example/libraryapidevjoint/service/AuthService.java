package com.example.libraryapidevjoint.service;

import com.example.libraryapidevjoint.dto.request.RegisterRequestDto;

public interface AuthService {
    void register(RegisterRequestDto request);
}
