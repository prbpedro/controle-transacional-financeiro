package com.github.prbpedro.ctf.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.prbpedro.ctf.validation.validator.TransactionAccountIdValidator;

@Documented
@Constraint(validatedBy = TransactionAccountIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionAccountIdConstraint {
    String message() default "accountId inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}