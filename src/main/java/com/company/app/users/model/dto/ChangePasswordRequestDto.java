package com.company.app.users.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.company.app.common.constants.PasswordConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordRequestDto {
    
    @Pattern(regexp = PasswordConstants.PASSWORD_CHECK_REGEX, message = PasswordConstants.PASSWORD_CHECK_MESSAGE)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
