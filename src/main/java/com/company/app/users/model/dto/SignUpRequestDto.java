package com.company.app.users.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.codes.UserTypes;
import com.company.app.common.configs.validations.EnumTypeValid;
import com.company.app.common.constants.PasswordConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @Pattern(regexp = PasswordConstants.PASSWORD_CHECK_REGEX, message = PasswordConstants.PASSWORD_CHECK_MESSAGE)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "userType을 입력해주세요.")
    @EnumTypeValid(target = UserTypes.class, message = "올바른 userType을 입력해주세요")
    private String userType;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "gender을 입력해주세요.")
    @EnumTypeValid(target = GenderTypes.class, message = "올바른 gender를 입력해주세요")
    private String gender;

    @NotNull(message = "생년월일을 입력해주세요.")
    @JsonFormat(pattern = "yyyyMMdd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    /**
     * 아이정보 - 부모 : required
     */
    @Valid
    private List<ChildInfoRequestDto> kidsInfo;
    /**
     * 신청 내용 - 부모 : required
     */
    private String requestInfo;

    /**
     * 시터회원이 케어 가능한 최소 연령
     */
    private int minCareAge;
    /**
     * 시터회원이 케어 가능한 최대 연령
     */
    private int maxCareAge;

    /**
     * 자기 소개
     */
    private String introduce;
}
