package com.github.prbpedro.ctf.unittests.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.prbpedro.ctf.entidades.OperationType;
import com.github.prbpedro.ctf.repositorios.OperationTypeRepository;
import com.github.prbpedro.ctf.services.IOperationTypeService;

@SpringBootTest
public class OperationTypeServiceUnitTests {

	@Autowired
	private IOperationTypeService operationTypeService;

	@MockBean
	private OperationTypeRepository operationTypeRepository;
	
	@Test
	void existsSucessTest() {
		Mockito.when(operationTypeRepository.existsById(1)).thenReturn(true);
		boolean resp = operationTypeService.exists(1);
		assertTrue(resp);
	}
	
	@Test
	void existsFailTest() {
		Mockito.when(operationTypeRepository.existsById(1)).thenReturn(false);
		boolean resp = operationTypeService.exists(1);
		assertFalse(resp);
	}
	
	@Test
	void existsExceptionTest() {
		Mockito.when(operationTypeRepository.existsById(1)).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, () -> {
			operationTypeService.exists(1);
		});
	}
	
	@Test
	void persistDefaultValuesSucessTest() {
		Mockito.when(operationTypeRepository.save(Mockito.any())).thenReturn(new OperationType(1, "TESTE"));
		operationTypeService.persistDefaultValues();
		assertTrue(true);
	}
	
	@Test
	void persistDefaultValuesExceptionFailTest() {
		Mockito.when(operationTypeRepository.save(Mockito.any())).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, () -> {
			operationTypeService.persistDefaultValues();
		});
	}
	
}
