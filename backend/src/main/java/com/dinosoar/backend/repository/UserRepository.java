package com.dinosoar.backend.repository;

import com.dinosoar.backend.model.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @NullMarked
    List<User> findAll();

    @EntityGraph(attributePaths = {"roles"})
    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
