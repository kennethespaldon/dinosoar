package com.dinosoar.backend.controller;

import com.dinosoar.backend.dto.UserDTO;
import com.dinosoar.backend.dto.UserDTOMapper;
import com.dinosoar.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(userDTOMapper).toList();
    }

    @PostMapping(
            value = "{userId}/profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadUserProfileImage(
            @PathVariable Integer userId,
            @RequestParam("file") MultipartFile file) {
        userService.uploadUserProfileImage(userId, file);
    }

    @GetMapping("{userId}/profile-image")
    public byte[] getUserProfileImage(@PathVariable Integer userId) {
        return userService.getUserProfileImage(userId);
    }


}
