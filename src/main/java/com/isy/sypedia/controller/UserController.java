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

//    {
//  "adult": false,
//  "backdrop_path": "/9DYFYhmbXRGsMhfUgXM3VSP9uLX.jpg",
//  "belongs_to_collection": {
//    "id": 313086,
//    "name": "The Conjuring Collection",
//    "poster_path": "/z5VKhNSQKQyxm0co68HAkCqHnmX.jpg",
//    "backdrop_path": "/kHZaX0vuhZdbuq0WKU3BpA9WIQ0.jpg"
//  },
//  "budget": 55000000,
//  "genres": [
//    {
//      "id": 27,
//      "name": "Horror"
//    }
//  ],
//  "homepage": "http://www.theconjuringmovie.com",
//  "id": 1038392,
//  "imdb_id": "tt22898462",
//  "origin_country": [
//    "US"
//  ],
//  "original_language": "en",
//  "original_title": "The Conjuring: Last Rites",
//  "overview": "Paranormal investigators Ed and Lorraine Warren take on one last terrifying case involving mysterious entities they must confront.",
//  "popularity": 671.1516,
//  "poster_path": "/7JzOmJ1fIU43I3gLHYsY8UzNzjG.jpg",
//  "production_companies": [
//    {
//      "id": 12,
//      "logo_path": "/2ycs64eqV5rqKYHyQK0GVoKGvfX.png",
//      "name": "New Line Cinema",
//      "origin_country": "US"
//    },
//    {
//      "id": 76907,
//      "logo_path": "/ygMQtjsKX7BZkCQhQZY82lgnCUO.png",
//      "name": "Atomic Monster",
//      "origin_country": "US"
//    },
//    {
//      "id": 11565,
//      "logo_path": "/odU3l6csuBmXUrzFm6araUgEUHQ.png",
//      "name": "The Safran Company",
//      "origin_country": "US"
//    },
//    {
//      "id": 216687,
//      "logo_path": "/kKVYqekveOvLK1IgqdJojLjQvtu.png",
//      "name": "Domain Entertainment",
//      "origin_country": "US"
//    }
//  ],
//  "production_countries": [
//    {
//      "iso_3166_1": "US",
//      "name": "United States of America"
//    }
//  ],
//  "release_date": "2025-09-03",
//  "revenue": 473042072,
//  "runtime": 135,
//  "spoken_languages": [
//    {
//      "english_name": "English",
//      "iso_639_1": "en",
//      "name": "English"
//    }
//  ],
//  "status": "Released",
//  "tagline": "The case that ended it all.",
//  "title": "The Conjuring: Last Rites",
//  "video": false,
//  "vote_average": 7,
//  "vote_count": 887
//}

}


