package com.dinosoar.backend.auth.dto;

public record LoginRequest (
        String email,
        String password
) {}
