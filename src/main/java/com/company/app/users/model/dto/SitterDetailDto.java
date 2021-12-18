package com.company.app.users.model.dto;

import com.company.app.users.model.SitterDetailModel;

import lombok.Data;

@Data
public class SitterDetailDto {
    
    private Integer minCareAge;
    private Integer maxCareAge;
    private String introduce;

    public SitterDetailDto(SitterDetailModel sitter){
        this.minCareAge = sitter.getMinCareAge();
        this.maxCareAge = sitter.getMaxCareAge();
        this.introduce = sitter.getIntroduce();
    }
}
