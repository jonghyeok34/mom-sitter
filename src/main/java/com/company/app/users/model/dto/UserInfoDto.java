package com.company.app.users.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.codes.UserTypes;
import com.company.app.users.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private Long memberNo;

    private String userId;

    private String name;

    private LocalDate birthdate;

    private String email;

    private GenderTypes gender;

    private List<UserTypes> userType;

    // 돌봄 신청 내용
    // @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestInfo;

    // @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ChildInfoDto> childInfoList;

    // @JsonInclude(JsonInclude.Include.NON_NULL)
    private SitterDetailDto sitterDetail;

    public UserInfoDto(UserModel user) {
        this.memberNo = user.getMemberNo();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.birthdate = user.getBirthdate();
        this.email = user.getEmail();
        this.gender = user.getGender();
        final List<UserTypes> userTypes = user.getUserType();
        if (userTypes != null) {
            this.userType = userTypes;
            if (userTypes.contains(UserTypes.PARENT)) {
                this.requestInfo = user.getRequestInfo();
                if(user.getChildInfoList() != null && user.getChildInfoList().size() >0)
                this.childInfoList = user.getChildInfoList().stream().map(ChildInfoDto::new).collect(Collectors.toList());
            }
            if (userTypes.contains(UserTypes.SITTER)) {
                
                if(user.getSitterDetail() != null )
                    this.sitterDetail = new SitterDetailDto(user.getSitterDetail());
            }
        }
    }
}
