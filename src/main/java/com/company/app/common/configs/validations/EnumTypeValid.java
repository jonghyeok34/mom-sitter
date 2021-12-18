package com.company.app.common.configs.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EnumValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumTypeValid {

    String message() default "invalid parameter!!";
    Class<?>[] groups() default {};
    Class<? extends Enum<?>> target();
    Class<? extends Payload>[] payload() default{};

}