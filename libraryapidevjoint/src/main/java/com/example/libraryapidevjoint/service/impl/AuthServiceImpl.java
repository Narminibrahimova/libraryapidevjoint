package com.example.libraryapidevjoint.service.impl;


import com.example.libraryapidevjoint.dto.request.RegisterRequestDto;
import com.example.libraryapidevjoint.entity.AppUser;
import com.example.libraryapidevjoint.entity.Role;
import com.example.libraryapidevjoint.exception.ResourceNotFoundException;
import com.example.libraryapidevjoint.mapper.UserMapper;
import com.example.libraryapidevjoint.repository.RoleRepository;
import com.example.libraryapidevjoint.repository.UserRepository;
import com.example.libraryapidevjoint.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  @Override
  public void register(RegisterRequestDto request) {
      if (userRepository.findByEmail(request.getEmail()).isPresent()) {
          throw new IllegalArgumentException("Email already exists");
      }
    AppUser appUser = userMapper.toEntity(request);
    Role role = roleRepository.findByName("USER").orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    appUser.setPassword(passwordEncoder.encode(request.getPassword()));
    appUser.setRole(role);
    userRepository.save(appUser);
  }
}
