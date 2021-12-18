package com.company.app.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.codes.UserTypes;
import com.company.app.common.exceptions.ExistingUserException;
import com.company.app.common.exceptions.FormValueRequiredExcpetion;
import com.company.app.common.exceptions.UserAuthException;
import com.company.app.common.utils.JwtTokenUtil;
import com.company.app.users.model.ChildInfoModel;
import com.company.app.users.model.SitterDetailModel;
import com.company.app.users.model.UserModel;
import com.company.app.users.model.UserToken;
import com.company.app.users.model.dto.LoginRequestDto;
import com.company.app.users.model.dto.LoginResponseDto;
import com.company.app.users.model.dto.SignUpRequestDto;
import com.company.app.users.model.dto.SignUpResponseDto;
import com.company.app.users.model.dto.UserInfoDto;
import com.company.app.users.repository.UserModelRepository;
import com.company.app.users.repository.UserTokenRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsersServiceImp implements UsersService {

    private final UserModelRepository userModelRepository;
    private final UserTokenRepository userTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void validateSignUp(SignUpRequestDto form) {
        // 부모일 경우 아이 정보, 신청 내용 확인
        UserTypes userType = UserTypes.valueOf(form.getUserType());
        if (UserTypes.PARENT == userType) {

            if (form.getKidsInfo() == null || form.getKidsInfo().size() == 0) {
                throw new FormValueRequiredExcpetion("아이 정보를 입력해주셔야 합니다.");
            }
            // if (!StringUtils.hasText(form.getRequestInfo())) {
            // throw new FormValueRequiredExcpetion("신청내용을 입력해주셔야 합니다.");
            // }
        }

        // 시터회원일 경우 케어 가능한 연령 범위, 자기소개 확인
        if (UserTypes.SITTER == userType) {

            if (form.getMinCareAge() < 0) {
                throw new FormValueRequiredExcpetion("minCareAge는 0보다 크게 입력해주셔야 합니다.");
            }
            if (form.getMaxCareAge() <= form.getMinCareAge()) {
                throw new FormValueRequiredExcpetion("maxCareAge는 minCareAge보다 커야합니다.");
            }
        }
    }

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto form) {

        UserModel user = userModelRepository.findByEmail(form.getEmail());
        UserModel userById = userModelRepository.findByUserId(form.getUserId());

        if (user != null || userById != null) {
            throw new ExistingUserException("이미 가입된 유저입니다");
        } else {

            List<UserTypes> userTypes = new ArrayList<>();
            UserTypes userType = UserTypes.valueOf(form.getUserType());
            userTypes.add(userType);
            UserModel newUser = UserModel.builder()
                    .email(form.getEmail())
                    .name(form.getName())
                    .birthdate(form.getBirthDate())
                    .gender(GenderTypes.valueOf(form.getGender()))
                    .userId(form.getUserId())
                    .password(passwordEncoder.encode(form.getPassword()))
                    .userType(userTypes).build();

            if (UserTypes.PARENT.equals(userType)) {
                newUser.setChildInfoList(
                        form.getKidsInfo().stream().map(ChildInfoModel::new).collect(Collectors.toList()));

            } else {
                newUser.setSitterDetail(
                        SitterDetailModel.builder()
                                .minCareAge(form.getMinCareAge())
                                .maxCareAge(form.getMaxCareAge())
                                .introduce(form.getIntroduce())
                                .build());
            }
            newUser = userModelRepository.save(newUser);

            SignUpResponseDto dto = SignUpResponseDto.builder()
                    .memberNo(newUser.getMemberNo())
                    .updatedAt(newUser.getUpdatedAt())
                    .createdAt(newUser.getCreatedAt())
                    .build();
            return dto;
        }
    }

    @Override
    public LoginResponseDto login(LoginRequestDto form) {

        UserModel user = userModelRepository.findByUserId(form.getUserId());
        if (user == null) {
            throw new UserAuthException("아이디를 확인해주세요");
        }
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new UserAuthException("비밀번호를 확인해주세요");
        }
        String token = jwtTokenUtil.generateToken(user.getUserId());
        userTokenRepository.save(
                UserToken.builder()
                        .userId(user.getUserId())
                        .token(token).build());

        // operation.set(tokenKey, token);

        return new LoginResponseDto(token);

    }

    @Override
    public UserInfoDto myInfo() {
        HttpServletRequest request = getServletRequest();
        log.info("is null :{}", request.getAttribute("userInfo") ==null); 
        return new UserInfoDto((UserModel) request.getAttribute("userInfo"));

    }

    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request;
        } else {
            return null;
        }
    }

}
