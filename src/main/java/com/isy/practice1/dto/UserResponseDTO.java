package com.isy.practice1.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class UserResponseDTO {
    private Integer userNo;
    private String userId;
    private Instant createdAt;
}
