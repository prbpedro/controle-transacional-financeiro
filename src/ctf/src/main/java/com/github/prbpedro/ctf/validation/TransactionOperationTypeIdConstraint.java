package com.github.prbpedro.ctf.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.prbpedro.ctf.validation.validator.TransactionOperationTypeIdValidator;

@Documented
@Constraint(validatedBy = TransactionOperationTypeIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionOperationTypeIdConstraint {
    String message() default "operationTypeId inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}