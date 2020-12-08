package com.github.prbpedro.ctf.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.github.prbpedro.ctf.repositorios.AccountRepository;
import com.github.prbpedro.ctf.validation.TransactionAccountIdConstraint;

@Component
public class TransactionAccountIdValidator implements ConstraintValidator<TransactionAccountIdConstraint, Long>, ApplicationContextAware {

	private AccountRepository accountRepository;
	@Override
	public void initialize(TransactionAccountIdConstraint constraint) {
	}

	@Override
	public boolean isValid(Long accountId, ConstraintValidatorContext cxt) {
		return accountId != null && accountRepository.existsById(accountId);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		accountRepository = applicationContext.getBean(AccountRepository.class);
	}

}