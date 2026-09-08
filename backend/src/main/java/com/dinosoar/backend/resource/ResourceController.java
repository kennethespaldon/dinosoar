package com.dinosoar.backend.resource;

import com.dinosoar.backend.resource.dto.ResourceCreationRequest;
import com.dinosoar.backend.resource.dto.ResourceCreationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ResourceCreationResponse> uploadResource(
        @Valid @ModelAttribute ResourceCreationRequest resourceCreationRequest
    ) throws IOException {
        Resource addedResource = resourceService.addResource(
                resourceCreationRequest.key(),
                resourceCreationRequest.file().getBytes()
        );
        long resourceId = addedResource.getId();
        ResourceCreationResponse responseBody =
                new ResourceCreationResponse(
                        resourceCreationRequest.file().getOriginalFilename(),
                        resourceId
                );

        return ResponseEntity
                .created(URI.create("/api/resources/" + resourceId))
                .body(responseBody);
    }
}
