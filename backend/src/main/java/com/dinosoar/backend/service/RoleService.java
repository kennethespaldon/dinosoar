package com.dinosoar.backend.service;

import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.exception.ResourceNotFoundException;
import com.dinosoar.backend.model.Role;
import com.dinosoar.backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(RoleType type) {
        return roleRepository
                .findRoleByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }
}
