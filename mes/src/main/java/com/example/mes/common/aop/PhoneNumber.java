package com.example.mes.common.aop;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface PhoneNumber {
    String message() default "전화번호 형식이 올바르지 않습니다. 올바른 형식: 010-1234-5678";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}