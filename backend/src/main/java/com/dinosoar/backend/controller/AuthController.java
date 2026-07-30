package com.dinosoar.backend.controller;

import com.dinosoar.backend.dto.*;
import com.dinosoar.backend.model.User;
import com.dinosoar.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    private void persistAuthentication(Authentication auth, HttpServletRequest httpRequest) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        persistAuthentication(auth, httpRequest);
        User user = (User) auth.getPrincipal();
        UserDTO userDTO = userDTOMapper.apply(user);

        return ResponseEntity.ok(new LoginResponse(userDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegistrationRequest request, HttpServletRequest httpRequest) {
        User addedUser = userService.addStudent(request);
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        persistAuthentication(auth, httpRequest);
        UserDTO userDTO = userDTOMapper.apply(addedUser);

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/users/{id}")
                                .buildAndExpand(userDTO.id())
                                .toUri()
                )
                .body(userDTO);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> currentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userDTOMapper.apply(user));
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }
}
