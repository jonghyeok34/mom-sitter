package com.company.app.users.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
// @AllArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
    @NotBlank(message="userId를 입력해주세요.")
    private String userId;
    @NotBlank(message="비밀번호를 입력해주세요.")
    private String password;
}
