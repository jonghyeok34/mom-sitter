package com.company.app.common.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

import com.company.app.common.codes.UserTypes;

import org.springframework.util.StringUtils;

public class UserTypesListConverter implements AttributeConverter<List<UserTypes>, String>  {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<UserTypes> enumList) {
        return String.join(SPLIT_CHAR, enumList.stream().map(UserTypes::name).collect(Collectors.toList()));
    }

    @Override
    public List<UserTypes> convertToEntityAttribute(String string) {
        
        // return Arrays.asList(string.split(SPLIT_CHAR)).;
        if (!StringUtils.hasText(string)) return null;
        else return Arrays.asList(string.split(SPLIT_CHAR)).stream().map(UserTypes::valueOf).collect(Collectors.toList());
        
    }
}
