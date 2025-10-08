package com.isy.sypedia.service;

import com.isy.sypedia.dto.LoginRequestDTO;
import com.isy.sypedia.dto.SignupRequestDTO;
import com.isy.sypedia.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(SignupRequestDTO dto);
    String login(LoginRequestDTO dto);

}
