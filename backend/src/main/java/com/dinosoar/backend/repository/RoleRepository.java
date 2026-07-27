package com.dinosoar.backend.repository;

import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleByType(RoleType type);
}