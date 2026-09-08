package com.dinosoar.backend.user.dto;


import com.dinosoar.backend.enums.RoleType;

import java.util.Set;

public record UserDTO (
        Long id,
        String email,
        String firstName,
        String lastName,
        Set<RoleType> roles,
        String profileImageId
) {}
