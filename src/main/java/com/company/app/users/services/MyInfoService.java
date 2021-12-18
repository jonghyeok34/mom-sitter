package com.company.app.users.services;

import javax.servlet.http.HttpServletRequest;

import com.company.app.common.services.ApiBaseService;
import com.company.app.users.model.UserModel;
import com.company.app.users.model.dto.UserInfoDto;

import org.springframework.stereotype.Service;

@Service
public class MyInfoService extends ApiBaseService {
    // 3. 내 정보 업데이트
    // 4. 부모로도 활동하기
    // 5. 시터로도 활동하기
    // 6. 내정보 보기
    public UserInfoDto myInfo() {
        HttpServletRequest request = getServletRequest();
        return new UserInfoDto((UserModel) request.getAttribute("userInfo"));

    }
}
