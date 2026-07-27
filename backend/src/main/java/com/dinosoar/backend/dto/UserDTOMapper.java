package com.dinosoar.backend.dto;

import com.dinosoar.backend.model.Role;
import com.dinosoar.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
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
                        .collect(Collectors.toList())
        );
    }
}
