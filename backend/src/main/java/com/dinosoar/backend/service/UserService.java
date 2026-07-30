package com.dinosoar.backend.service;

import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.dto.UserRegistrationRequest;
import com.dinosoar.backend.exception.DuplicateResourceException;
import com.dinosoar.backend.exception.ResourceNotFoundException;
import com.dinosoar.backend.model.Role;
import com.dinosoar.backend.model.User;
import com.dinosoar.backend.repository.RoleRepository;
import com.dinosoar.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private User createUser(UserRegistrationRequest request, RoleType type) {
        String email = request.email();
        if (userRepository.existsUserByEmail(email)) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User(
                email,
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName()
        );
        Role role = roleRepository.findRoleByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.addRole(role);

        return user;
    }

    @Transactional
    public User addStudent(UserRegistrationRequest request) {
        User user = createUser(request, RoleType.STUDENT);
        return userRepository.save(user);
    }

    @Transactional
    public User addInstructor(UserRegistrationRequest request) {
        User user = createUser(request, RoleType.INSTRUCTOR);
        return userRepository.save(user);
    }

    @Transactional
    public User addAdmin(UserRegistrationRequest request) {
        User user = createUser(request, RoleType.ADMIN);
        return userRepository.save(user);
    }

//    @Transactional
//    public void addRoleToUser(int userId, int roleId) {
//        User user = userRepository
//                .findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        Role role = roleRepository
//                .findById(roleId)
//                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
//
//        user.addRole(role);
//        userRepository.save(user);
//    }
}
