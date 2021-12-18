package com.company.app.users.services;

import com.company.app.users.model.dto.LoginRequestDto;
import com.company.app.users.model.dto.LoginResponseDto;
import com.company.app.users.model.dto.SignUpRequestDto;
import com.company.app.users.model.dto.SignUpResponseDto;
import com.company.app.users.model.dto.UserInfoDto;




public interface UsersService {
    public void validateSignUp(SignUpRequestDto form);
    public SignUpResponseDto signUp(SignUpRequestDto form);
    public LoginResponseDto login(LoginRequestDto form);
    public UserInfoDto myInfo();
}
