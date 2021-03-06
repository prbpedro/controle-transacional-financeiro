package com.github.prbpedro.ctf.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.prbpedro.ctf.util.Constantes;
import com.github.prbpedro.ctf.validation.validator.TransactionAccountIdValidator;

@Documented
@Constraint(validatedBy = TransactionAccountIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionAccountIdConstraint {
	String message() default Constantes.ACCOUNT_ID_INVALIDO;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}