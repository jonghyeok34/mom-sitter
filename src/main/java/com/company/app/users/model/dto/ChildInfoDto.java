package com.company.app.users.model.dto;

import java.time.LocalDate;

import com.company.app.common.codes.GenderTypes;
import com.company.app.users.model.ChildInfoModel;

import lombok.Data;

@Data
public class ChildInfoDto {
    private Long childNo;

    private GenderTypes gender;

    private LocalDate birthdate;

    public ChildInfoDto(ChildInfoModel childInfo){
        this.childNo = childInfo.getChildNo();
        this.gender = childInfo.getGender();
        this.birthdate = childInfo.getBirthdate();
    }
}
