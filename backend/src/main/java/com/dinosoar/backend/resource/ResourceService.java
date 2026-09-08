package com.dinosoar.backend.resource;

import com.dinosoar.backend.s3.S3Buckets;
import com.dinosoar.backend.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public Resource addResource(String storageKey, byte[] file) {
        Resource resource = new Resource(storageKey);
        s3Service.putObject(s3Buckets.getDinosoar(), storageKey, file);
        return resourceRepository.save(resource);
    }
}
