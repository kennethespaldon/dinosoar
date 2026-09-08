package com.dinosoar.backend.user.dto;

import com.dinosoar.backend.user.role.Role;
import com.dinosoar.backend.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
                        .stream()
                        .map(Role::getType)
                        .collect(Collectors.toSet()),
                user.getProfileImageId()
        );
    }
}
