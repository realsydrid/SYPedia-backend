package com.isy.sypedia.controller;

import com.isy.sypedia.dto.LoginRequestDTO;
import com.isy.sypedia.dto.SignupRequestDTO;
import com.isy.sypedia.dto.UserResponseDTO;
import com.isy.sypedia.service.UserService;
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

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        UserResponseDTO response = userService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}


