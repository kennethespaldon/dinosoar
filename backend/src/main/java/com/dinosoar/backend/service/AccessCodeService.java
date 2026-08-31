package com.dinosoar.backend.service;

import com.dinosoar.backend.model.AccessCode;
import com.dinosoar.backend.repository.AccessCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessCodeService {

    private final AccessCodeRepository accessCodeRepository;

    public AccessCode save(String code) {
        return accessCodeRepository.save(new AccessCode(code));
    }

    public void delete(String code) {
        accessCodeRepository.deleteByCode(code);
    }

    public boolean checkIfCodeExists(String code) {
        return accessCodeRepository.existsByCode(code);
    }
}
