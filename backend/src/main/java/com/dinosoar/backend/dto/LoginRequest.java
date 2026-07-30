package com.dinosoar.backend.dto;

public record LoginRequest (
        String email,
        String password
) {}
