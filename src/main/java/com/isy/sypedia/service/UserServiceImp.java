package com.isy.sypedia.service;

import com.isy.sypedia.dto.LoginRequestDTO;
import com.isy.sypedia.dto.SignupRequestDTO;
import com.isy.sypedia.dto.UserResponseDTO;
import com.isy.sypedia.entity.User;
import com.isy.sypedia.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDTO register(SignupRequestDTO dto) {

        User user = User.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .build();

        User saved = userRepository.save(user);

        return UserResponseDTO.builder()
                .userNo(saved.getUserNo())
                .userId(saved.getUserId())
                .createdAt(saved.getCreatedAt())
                .build();

    }

    @Override
    public UserResponseDTO login(LoginRequestDTO dto) {

        String reqUserId = dto.getUserId();
        String reqPassword = dto.getPassword();
        User user = userRepository.findByUserId(reqUserId)
                .orElseThrow(()->new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if(!user.getPassword().equals(reqPassword)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return UserResponseDTO .builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .build();

    }
}
