package com.dinosoar.backend.controller;

import com.dinosoar.backend.dto.UserDTO;
import com.dinosoar.backend.dto.UserRegistrationRequest;
import com.dinosoar.backend.model.User;
import com.dinosoar.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // DELETE THIS
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        User user = userService.addStudent(request);
        UserDTO userDTO = userService.getUser(user.getId());
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + userDTO.id().toString())
                                .build()
                                .toUri()
                )
                .body(userDTO);
    }
}
