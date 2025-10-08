package com.isy.sypedia.controller;

import com.isy.sypedia.dto.LoginRequestDTO;
import com.isy.sypedia.dto.SignupRequestDTO;
import com.isy.sypedia.dto.UserResponseDTO;
import com.isy.sypedia.security.JwtUtil;
import com.isy.sypedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private UserService userService;
    private JwtUtil jwtUtil;



    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO dto) {
        UserResponseDTO response = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        String token = userService.login(dto);

        ResponseCookie cookie = ResponseCookie.from("ACCESS_TOKEN", token)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("userNo", jwtUtil.getUserNoFromJwtToken(token)));
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout() {

        ResponseCookie delete = ResponseCookie.from("ACCESS_TOKEN", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, delete.toString())
                .build();
    }

}


