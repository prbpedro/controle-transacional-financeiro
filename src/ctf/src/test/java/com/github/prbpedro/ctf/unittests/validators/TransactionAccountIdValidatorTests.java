package com.github.prbpedro.ctf.unittests.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.prbpedro.ctf.repositorios.AccountRepository;
import com.github.prbpedro.ctf.validation.validator.TransactionAccountIdValidator;

@SpringBootTest
public class TransactionAccountIdValidatorTests {

	@MockBean
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionAccountIdValidator validator;
	
	@Test
	void validatorSucessTest() {
		Mockito.when(accountRepository.existsById(777L)).thenReturn(true);
		validator.initialize(null);
		
		boolean valid = validator.isValid(777L, null);
		
		assertTrue(valid);
	}
	
	@Test
	void validatorFailTest() {
		Mockito.when(accountRepository.existsById(888L)).thenReturn(false);
		validator.initialize(null);
		
		boolean valid = validator.isValid(888L, null);
		
		assertFalse(valid);
	}
	
	@Test
	void validatorNullFailsTest() {
		validator.initialize(null);
		
		boolean valid = validator.isValid(null, null);
		
		assertFalse(valid);
	}
}
