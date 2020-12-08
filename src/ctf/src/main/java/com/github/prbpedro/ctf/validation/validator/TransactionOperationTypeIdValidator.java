package com.github.prbpedro.ctf.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.github.prbpedro.ctf.repositorios.OperationTypeRepository;
import com.github.prbpedro.ctf.validation.TransactionOperationTypeIdConstraint;

@Component
public class TransactionOperationTypeIdValidator implements ConstraintValidator<TransactionOperationTypeIdConstraint, Integer>,  ApplicationContextAware {
	
	private OperationTypeRepository operationTypeRepository;

	@Override
	public void initialize(TransactionOperationTypeIdConstraint constraint) {
	}

	@Override
	public boolean isValid(Integer operationTypeId, ConstraintValidatorContext cxt) {
		return operationTypeId != null && operationTypeRepository.existsById(operationTypeId);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 operationTypeRepository = applicationContext.getBean(OperationTypeRepository.class);
	}

}