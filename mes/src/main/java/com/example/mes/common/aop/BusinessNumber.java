package com.example.mes.common.aop;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.*;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface BusinessNumber {
    String message() default "사업자 번호 형식이 올바르지 않습니다. 올바른 형식: 120-345-67890";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}