package com.isy.practice1.service;

import com.isy.practice1.dto.SignupRequestDTO;
import com.isy.practice1.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(SignupRequestDTO dto);

}
