package com.dinosoar.backend.service;

import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.dto.UserDTO;
import com.dinosoar.backend.dto.UserDTOMapper;
import com.dinosoar.backend.dto.UserRegistrationRequest;
import com.dinosoar.backend.exception.DuplicateResourceException;
import com.dinosoar.backend.exception.ResourceNotFoundException;
import com.dinosoar.backend.model.Role;
import com.dinosoar.backend.model.User;
import com.dinosoar.backend.repository.RoleRepository;
import com.dinosoar.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDTOMapper userDTOMapper;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userDTOMapper)
                .toList();
    }

    public UserDTO getUser(Integer id) {
        return userRepository
                .findById(id)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private User createUser(UserRegistrationRequest request) {
        String email = request.email();
        if (userRepository.existsUserByEmail(email)) {
            throw new DuplicateResourceException("Email already exists");
        }

        return new User(
                email,
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName()
        );
    }

    public User addStudent(UserRegistrationRequest request) {
        User user = createUser(request);
        Role role = roleRepository.findRoleByType(RoleType.STUDENT)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.addRole(role);

        return userRepository.save(user);
    }

    @Transactional
    public void addRoleToUser(int userId, int roleId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        user.addRole(role);
        userRepository.save(user);
    }
}
