package com.company.app.common.configs.validations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumTypeValid, String> {
    private List<String> types;

    @Override
    public void initialize(EnumTypeValid constraintAnnotation) {
        types = Arrays.stream(constraintAnnotation.target().getEnumConstants())
                      .map(constant -> constant.name())
                      .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        return types.contains(value);
    }
}