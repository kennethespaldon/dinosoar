package com.dinosoar.backend.repository;

import com.dinosoar.backend.model.AccessCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessCodeRepository extends JpaRepository<AccessCode, String> {
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
