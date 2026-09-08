package com.dinosoar.backend.user;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserById(Long id);
    Optional<User> findById(Long id);

    @NullMarked
    List<User> findAll();

    @EntityGraph(attributePaths = {"roles"})
    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);

    @Modifying
    @NativeQuery("""
        UPDATE users
        SET profile_image_id = ?
        WHERE id = ?
     """)
    void uploadCustomerProfileImageId(String profileImageId, Long userId);
}
