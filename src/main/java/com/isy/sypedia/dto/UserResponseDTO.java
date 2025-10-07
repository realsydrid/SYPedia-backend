package com.isy.sypedia.dto;


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
