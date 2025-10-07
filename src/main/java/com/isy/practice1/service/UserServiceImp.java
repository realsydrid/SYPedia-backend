package com.isy.practice1.service;

import com.isy.practice1.dto.SignupRequestDTO;
import com.isy.practice1.dto.UserResponseDTO;
import com.isy.practice1.entity.User;
import com.isy.practice1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

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
}
