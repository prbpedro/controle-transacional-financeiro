package com.github.prbpedro.ctf.unittests.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.prbpedro.ctf.repositorios.OperationTypeRepository;
import com.github.prbpedro.ctf.validation.validator.TransactionOperationTypeIdValidator;

@SpringBootTest
public class TransactionOperationTypeIdValidatorTests {
	
	@MockBean
	private OperationTypeRepository operationTypeRepository;
	
	@Autowired
	private TransactionOperationTypeIdValidator validator;
	
	@Test
	void transactionOperationTypeIdValidatorSucessTest() {
		Mockito.when(operationTypeRepository.existsById(888)).thenReturn(true);
		
		validator.initialize(null);
		
		boolean valid = validator.isValid(888, null);
		
		assertTrue(valid);
	}
	
	@Test
	void transactionOperationTypeIdValidatorFailTest() {
		Mockito.when(operationTypeRepository.existsById(999)).thenReturn(false);
		
		validator.initialize(null);
		
		boolean valid = validator.isValid(999, null);
		
		assertFalse(valid);
	}
	
	@Test
	void transactionOperationTypeIdValidatorNullFailsTest() {
		validator.initialize(null);
		
		boolean valid = validator.isValid(null, null);
		
		assertFalse(valid);
	}
}
