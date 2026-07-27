package com.dinosoar.backend.dto;


import com.dinosoar.backend.enums.RoleType;

import java.util.List;

public record UserDTO (
        Integer id,
        String email,
        String firstName,
        String lastName,
        List<RoleType> roles
) {}
