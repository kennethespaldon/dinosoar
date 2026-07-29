package com.dinosoar.backend.service;

import com.dinosoar.backend.dto.UserDTO;
import com.dinosoar.backend.dto.UserDTOMapper;
import com.dinosoar.backend.dto.UserRegistrationRequest;
import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.exception.DuplicateResourceException;
import com.dinosoar.backend.exception.ResourceNotFoundException;
import com.dinosoar.backend.model.Role;
import com.dinosoar.backend.model.User;
import com.dinosoar.backend.repository.RoleRepository;
import com.dinosoar.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserDTOMapper userDTOMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, roleRepository, userDTOMapper, passwordEncoder);
    }

    private UserRegistrationRequest createTestRegistrationRequest() {
        return new UserRegistrationRequest(
                "test@email.com",
                "password",
                "John",
                "Doe"
        );
    }

    private void testUserCreationForRole(RoleType type, Function<UserRegistrationRequest, User> serviceMethod) {
        UserRegistrationRequest request = createTestRegistrationRequest();
        Role role = new Role(1, type);

        when(passwordEncoder.encode(request.password())).thenReturn("encoded-password");
        when(roleRepository.findRoleByType(type)).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1);

            return user;
        });

        User addedUser = serviceMethod.apply(request);

        assertNotNull(addedUser);
        assertEquals(1, addedUser.getId());
        assertEquals(request.email(), addedUser.getEmail());
        assertEquals(request.firstName(), addedUser.getFirstName());
        assertEquals(request.lastName(), addedUser.getLastName());
        assertEquals("encoded-password", addedUser.getPassword());
        assertTrue(addedUser.getRoles().stream().anyMatch(r -> r.getType() == type));

        verify(passwordEncoder).encode(request.password());
        verify(roleRepository).findRoleByType(type);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void addStudent_WithValidRequest_ReturnsUser() {
        testUserCreationForRole(RoleType.STUDENT, userService::addStudent);
    }

    @Test
    void addInstructor_WithValidRequest_ReturnsUser() {
        testUserCreationForRole(RoleType.INSTRUCTOR, userService::addInstructor);
    }

    @Test
    void addAdmin_WithValidRequest_ReturnsUser() {
        testUserCreationForRole(RoleType.ADMIN, userService::addAdmin);
    }

    @Test
    @DisplayName("Creating a user with an existing email throws a duplicate resource exception")
    void addStudent_WithExistingEmail_ThrowsDuplicateResourceException() {
        UserRegistrationRequest request = createTestRegistrationRequest();

        when(userRepository.existsUserByEmail(request.email())).thenReturn(true);

        assertThatThrownBy(() -> userService.addStudent(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Email already exists");

        verify(userRepository).existsUserByEmail(request.email());
        verify(userRepository, never()).save(any(User.class));
        verifyNoInteractions(passwordEncoder, roleRepository);
    }

    @Test
    @DisplayName("Creating a user with a nonexistent role throws a resource not found exception")
    void addStudent_WithNonExistentRole_ThrowsResourceNotFoundException() {
        UserRegistrationRequest request = createTestRegistrationRequest();

        when(passwordEncoder.encode(anyString())).thenReturn("encoded-password");
        when(roleRepository.findRoleByType(RoleType.STUDENT)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.addStudent(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Role not found");

        verify(roleRepository).findRoleByType(RoleType.STUDENT);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getAllUsers_WithNoUsers_ReturnsEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<UserDTO> fetchedUsers = userService.getAllUsers();

        assertThat(fetchedUsers).isEmpty();
        verify(userRepository).findAll();
        verifyNoInteractions(userDTOMapper);
    }

    @Test
    void getAllUsers_WithOneUser_ReturnsListWithOneUserDTO() {
        User user = new User("user1@email.com", "password", "user", "one");
        user.setId(1);
        user.addRole(new Role(1, RoleType.INSTRUCTOR));

        List<User> users = List.of(user);

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
                        .stream()
                        .map(Role::getType)
                        .collect(Collectors.toSet())
        );

        when(userRepository.findAll()).thenReturn(users);
        when(userDTOMapper.apply(user)).thenReturn(userDTO);

        List<UserDTO> fetchedUsers = userService.getAllUsers();

        assertThat(fetchedUsers).containsExactly(userDTO);
        verify(userRepository).findAll();
        verify(userDTOMapper).apply(user);
    }

    @Test
    void getAllUsers_WithMultipleUsers_ReturnsListOfUserDTOs() {
        User user = new User("user1@email.com", "password", "user", "one");
        user.setId(1);
        user.addRole(new Role(1, RoleType.INSTRUCTOR));

        User user2 = new User("user2@email.com", "password", "user", "two");
        user2.setId(2);
        user2.addRole(new Role(2, RoleType.STUDENT));

        List<User> users = List.of(user, user2);

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
                        .stream()
                        .map(Role::getType)
                        .collect(Collectors.toSet())
        );

        UserDTO userDTO2 = new UserDTO(
                user2.getId(),
                user2.getEmail(),
                user2.getFirstName(),
                user2.getLastName(),
                user2.getRoles()
                        .stream()
                        .map(Role::getType)
                        .collect(Collectors.toSet())
        );

        when(userRepository.findAll()).thenReturn(users);
        when(userDTOMapper.apply(user)).thenReturn(userDTO);
        when(userDTOMapper.apply(user2)).thenReturn(userDTO2);

        List<UserDTO> fetchedUsers = userService.getAllUsers();

        assertThat(fetchedUsers).containsExactly(userDTO, userDTO2);
        verify(userRepository).findAll();
        verify(userDTOMapper).apply(user);
        verify(userDTOMapper).apply(user2);
    }

    @Test
    void getUser_WithValidId_ShouldGetUserSuccessfully() {
        User user = new User("user@email.com", "password", "user", "one");
        user.setId(1);
        RoleType type = RoleType.STUDENT;
        Role role = new Role(1, type);
        user.addRole(role);

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
                        .stream()
                        .map(Role::getType)
                        .collect(Collectors.toSet())
        );

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userDTOMapper.apply(user)).thenReturn(userDTO);

        UserDTO fetchedUser = userService.getUser(user.getId());

        assertEquals(userDTO, fetchedUser);
        verify(userRepository).findById(user.getId());
        verify(userDTOMapper).apply(user);
    }

    @Test
    void getUser_WithNonExistentId_ThrowsResourceNotFoundException() {
        int userId = 1;

        assertThatThrownBy(() -> userService.getUser(userId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found");
        verify(userRepository).findById(userId);
        verifyNoInteractions(userDTOMapper);
    }
}