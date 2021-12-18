package com.company.app.users.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.codes.UserTypes;
import com.company.app.common.exceptions.FormValueRequiredExcpetion;
import com.company.app.common.exceptions.UserTypeException;
import com.company.app.common.services.ApiBaseService;
import com.company.app.users.model.ChildInfoModel;
import com.company.app.users.model.SitterDetailModel;
import com.company.app.users.model.UserModel;
import com.company.app.users.model.dto.AddParentTypeRequestDto;
import com.company.app.users.model.dto.AddSitterTypeRequestDto;
import com.company.app.users.model.dto.ChangePasswordRequestDto;
import com.company.app.users.model.dto.ChildInfoDto;
import com.company.app.users.model.dto.ChildInfoRequestDto;
import com.company.app.users.model.dto.UpdateChildInfoRequestDto;
import com.company.app.users.model.dto.UpdateMyInfoRequestDto;
import com.company.app.users.model.dto.UserInfoDto;
import com.company.app.users.repository.UserModelRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyInfoService extends ApiBaseService {

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;

    public void validateAddSitterType(AddSitterTypeRequestDto form) {

        // 시터회원일 경우 케어 가능한 연령 범위, 자기소개 확인

        if (form.getMinCareAge() < 0) {
            throw new FormValueRequiredExcpetion("minCareAge는 0보다 크게 입력해주셔야 합니다.");
        }
        if (form.getMaxCareAge() <= form.getMinCareAge()) {
            throw new FormValueRequiredExcpetion("maxCareAge는 minCareAge보다 커야합니다.");
        }

    }

    public void validateUpdateMyInfo(UpdateMyInfoRequestDto form) {

        // 시터회원일 경우 케어 가능한 연령 범위, 자기소개 확인
        if (form.getMinCareAge() < 0) {
            throw new FormValueRequiredExcpetion("minCareAge는 0보다 크게 입력해주셔야 합니다.");
        }
        if (form.getMaxCareAge() <= form.getMinCareAge()) {
            throw new FormValueRequiredExcpetion("maxCareAge는 minCareAge보다 커야합니다.");
        }

    }

    // 3. 내 정보 업데이트
    public UserInfoDto updateMyInfo(UpdateMyInfoRequestDto form) {
        UserModel user = getCurrentUser();
        // user.setBirthdate(birthdate);
        user.setName(form.getName());
        List<UserTypes> userTypes = user.getUserType();
        if (userTypes.contains(UserTypes.PARENT)) {
            user.setRequestInfo(form.getRequestInfo());
        }
        if (userTypes.contains(UserTypes.SITTER)) {
            SitterDetailModel sitterDetail = user.getSitterDetail();
            sitterDetail.setMinCareAge(form.getMinCareAge());
            sitterDetail.setMaxCareAge(form.getMaxCareAge());
            sitterDetail.setIntroduce(form.getIntroduce());
            user.setSitterDetail(sitterDetail);
        }
        return new UserInfoDto(userModelRepository.save(user));
    }

    // 비밀번호 변경
    public void changePassword(ChangePasswordRequestDto form) {
        UserModel user = getCurrentUser();
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userModelRepository.save(user);
    }

    // 아이 신규 등록
    public List<ChildInfoDto> putChildInfo(ChildInfoRequestDto form) {
        UserModel user = getCurrentUser();
        if (!user.getUserType().contains(UserTypes.PARENT)) {
            throw new UserTypeException("부모로 등록되지 않았습니다");
        }
        List<ChildInfoModel> childInfoList = user.getChildInfoList();
        childInfoList.add(new ChildInfoModel(form));
        user.setChildInfoList(
                childInfoList);

        return userModelRepository.save(user)
                .getChildInfoList()
                .stream().map(ChildInfoDto::new).collect(Collectors.toList());
    }

    // 아이 업데이트
    public List<ChildInfoDto> updateChildInfo(UpdateChildInfoRequestDto form) {
        UserModel user = getCurrentUser();
        user.getChildInfoList().stream()
                .forEach(item -> {
                    if (item.getChildNo().equals(form.getChildNo())) {
                        item.setBirthdate(form.getBirthDate());
                        item.setGender(GenderTypes.valueOf(form.getGender()));                       
                    }

                });
        
        return userModelRepository.save(user)
                .getChildInfoList()
                .stream().map(ChildInfoDto::new).collect(Collectors.toList());
    }

    // 4. 부모로도 활동하기
    public UserInfoDto addParentType(AddParentTypeRequestDto form) {
        UserModel user = getCurrentUser();
        List<UserTypes> userTypes = user.getUserType();
        if (userTypes.contains(UserTypes.PARENT))
            throw new UserTypeException("이미 부모로 활동중입니다.");
        // 부모로
        userTypes.add(UserTypes.PARENT);

        user.setChildInfoList(
                form.getKidsInfo().stream().map(ChildInfoModel::new).collect(Collectors.toList()));
        user.setUserType(
                userTypes);
        user.setRequestInfo(form.getRequestInfo());
        userModelRepository.save(user);

        return new UserInfoDto(user);
    }

    // 5. 시터로도 활동하기
    public UserInfoDto addSitterType(AddSitterTypeRequestDto form) {
        UserModel user = getCurrentUser();
        List<UserTypes> userTypes = user.getUserType();
        if (userTypes.contains(UserTypes.SITTER))
            throw new UserTypeException("이미 시터로 활동중입니다.");
        // 시터로
        userTypes.add(UserTypes.SITTER);
        user.setUserType(
                userTypes);

        user.setSitterDetail(
                SitterDetailModel.builder()
                        .introduce(form.getIntroduce())
                        .maxCareAge(form.getMaxCareAge())
                        .minCareAge(form.getMinCareAge())
                        .build());
        userModelRepository.save(user);

        return new UserInfoDto(user);
    }

    // 6. 내정보 보기
    public UserInfoDto myInfo() {
        HttpServletRequest request = getServletRequest();
        return new UserInfoDto((UserModel) request.getAttribute("userInfo"));
    }

}
