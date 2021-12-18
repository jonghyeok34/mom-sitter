package com.company.app.users.model.dto;

import java.util.List;

import javax.validation.Valid;
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
public class AddParentTypeRequestDto {
     /**
     * 아이정보 - 부모 : required
     */
    @Valid
    @NotNull(message="kidsInfo를 입력해야 합니다.")
    private List<ChildInfoRequestDto> kidsInfo;
    /**
     * 신청 내용 - 부모 : required
     */
    @NotBlank(message="requestInfo를 입력해야 합니다.")
    private String requestInfo;

}
