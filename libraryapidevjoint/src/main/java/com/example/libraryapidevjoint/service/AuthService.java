package com.example.libraryapidevjoint.service;

import com.example.libraryapidevjoint.dto.request.LoginRequestDto;
import com.example.libraryapidevjoint.dto.request.RegisterRequestDto;
import com.example.libraryapidevjoint.dto.response.LoginResponseDto;
import com.example.libraryapidevjoint.dto.response.RegisterResponseDto;

public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto request);
    LoginResponseDto login(LoginRequestDto request);
}
