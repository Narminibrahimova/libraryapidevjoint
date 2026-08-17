package com.example.libraryapidevjoint.service.impl;


import com.example.libraryapidevjoint.dto.request.LoginRequestDto;
import com.example.libraryapidevjoint.dto.request.RegisterRequestDto;
import com.example.libraryapidevjoint.dto.response.LoginResponseDto;
import com.example.libraryapidevjoint.dto.response.RegisterResponseDto;
import com.example.libraryapidevjoint.entity.AppUser;
import com.example.libraryapidevjoint.entity.Role;
import com.example.libraryapidevjoint.entity.RoleType;
import com.example.libraryapidevjoint.exception.ResourceNotFoundException;
import com.example.libraryapidevjoint.mapper.UserMapper;
import com.example.libraryapidevjoint.repository.RoleRepository;
import com.example.libraryapidevjoint.repository.UserRepository;
import com.example.libraryapidevjoint.security.CustomUserDetails;
import com.example.libraryapidevjoint.security.JwtService;
import com.example.libraryapidevjoint.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.Authenticator;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public RegisterResponseDto register(RegisterRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        AppUser user = userMapper.toEntity(request);
        String targetRole = (request.getRoleName() != null && !request.getRoleName().isBlank())
                ? request.getRoleName().toUpperCase()
                : RoleType.USER.name();

        Role role = roleRepository.findByName(targetRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + targetRole));

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        AppUser savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return LoginResponseDto.builder()
                .token(token)
                .build();
    }
}
