package com.company.app.users.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddSitterTypeRequestDto {

    /**
     * 시터회원이 케어 가능한 최소 연령
     */
    @NotNull(message = "minCareAge를 입력해야 합니다.")
    private Integer minCareAge;

    /**
     * 시터회원이 케어 가능한 최대 연령
     */
    @NotNull(message = "maxCareAge를 입력해야 합니다.")
    private Integer maxCareAge;

    /**
     * 자기 소개
     */
    @NotBlank(message = "introduce를 입력해야 합니다.")
    private String introduce;

}
