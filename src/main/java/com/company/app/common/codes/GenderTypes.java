package com.company.app.common.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderTypes {
    FEMALE("여자"),
    MALE("남자");
    // private String value;
    private String description;
}
