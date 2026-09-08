package com.dinosoar.backend.auth.dto;

import com.dinosoar.backend.user.dto.UserDTO;

public record LoginResponse(
        UserDTO userDTO
) {}
