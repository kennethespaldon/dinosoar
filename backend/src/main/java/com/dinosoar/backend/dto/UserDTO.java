package com.dinosoar.backend.dto;


import com.dinosoar.backend.enums.RoleType;

import java.util.Set;

public record UserDTO (
        Integer id,
        String email,
        String firstName,
        String lastName,
        Set<RoleType> roles,
        String profileImageId
) {}
