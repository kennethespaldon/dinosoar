package com.dinosoar.backend.user;

import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.user.dto.UserRegistrationRequest;
import com.dinosoar.backend.exception.DuplicateResourceException;
import com.dinosoar.backend.exception.ResourceNotFoundException;
import com.dinosoar.backend.user.role.Role;
import com.dinosoar.backend.auth.accesscode.AccessCodeService;
import com.dinosoar.backend.user.role.RoleService;
import com.dinosoar.backend.s3.S3Buckets;
import com.dinosoar.backend.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AccessCodeService accessCodeService;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private User createUser(UserRegistrationRequest request, RoleType type) {
        String accessCode = request.accessCode();
        if (!accessCodeService.checkIfCodeExists(accessCode)) {
            throw new ResourceNotFoundException("Invalid access code");
        }
        accessCodeService.delete(accessCode);

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
        Role role = roleService.getRole(type);
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

    public void uploadUserProfileImage(Long userId, MultipartFile file) {
        if (userRepository.existsUserById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }

        String profileImageId = UUID.randomUUID().toString();
        try {
            s3Service.putObject(
                    s3Buckets.getDinosoar(),
                    "profile-images/%s/%s".formatted(userId, profileImageId),
                    file.getBytes()
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to add upload profile image for [%s]".formatted(userId), e);
        }

        userRepository.uploadCustomerProfileImageId(profileImageId, userId);
    }

    public byte[] getUserProfileImage(Long userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        if (user.getProfileImageId() == null) {
            throw new ResourceNotFoundException("User with id [%s] profile image not found".formatted(userId));
        }

        byte[] profileImage = s3Service.getObject(
                s3Buckets.getDinosoar(),
                "profile-images/%s/%s".formatted(userId, user.getProfileImageId())
        );

        return profileImage;
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
