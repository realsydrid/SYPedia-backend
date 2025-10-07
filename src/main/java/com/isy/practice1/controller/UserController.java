package com.isy.practice1.controller;

import com.isy.practice1.dto.SignupRequestDTO;
import com.isy.practice1.dto.UserResponseDTO;
import com.isy.practice1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO dto) {
        UserResponseDTO response = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
