package com.dinosoar.backend.auth.accesscode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessCodeRepository extends JpaRepository<AccessCode, String> {
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
