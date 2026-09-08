package com.dinosoar.backend.resource.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ResourceCreationRequest(
        @NotBlank
        String key,

        MultipartFile file
) {}
