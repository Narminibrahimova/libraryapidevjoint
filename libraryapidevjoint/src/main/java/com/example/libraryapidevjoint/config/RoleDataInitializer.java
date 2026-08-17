package com.example.libraryapidevjoint.config;

import com.example.libraryapidevjoint.entity.Role;
import com.example.libraryapidevjoint.entity.RoleType;
import com.example.libraryapidevjoint.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for (RoleType roleType : RoleType.values()) {
            roleRepository.findByName(roleType.name())
                    .orElseGet(() -> roleRepository.save(
                            Role.builder().name(roleType.name()).build()
                    ));
        }
    }
}
