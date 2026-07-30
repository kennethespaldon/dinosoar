package com.dinosoar.backend.controller;

import com.dinosoar.backend.dto.UserDTO;
import com.dinosoar.backend.dto.UserDTOMapper;
import com.dinosoar.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(userDTOMapper).toList();
    }
}
