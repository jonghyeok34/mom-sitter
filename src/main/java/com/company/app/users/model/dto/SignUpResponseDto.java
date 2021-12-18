package com.company.app.users.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignUpResponseDto {
    // private String email;
    private Long memberNo;
    // private List<UserTypes> userTypes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;  
}
