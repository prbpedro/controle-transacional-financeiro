package com.github.prbpedro.ctf.integrationtests.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.prbpedro.ctf.entidades.OperationType;
import com.github.prbpedro.ctf.repositorios.OperationTypeRepository;

@DataJpaTest
public class OperationTypeRepositoryIntegretionTests {

	@Autowired
	private OperationTypeRepository operationTypeRepository;

	@Test
	public void saveSucessTest() {
		OperationType ot = new OperationType(999, "teste");
		OperationType resp = operationTypeRepository.save(ot);

		assertNotNull(resp);
		assertEquals(ot.getId(), resp.getId());
		assertEquals(ot.getDescription(), resp.getDescription());
	}

	@Test
	public void existsByIdSucessTest() {
		OperationType ot = new OperationType();
		ot.setId(999);
		ot.setDescription("teste");
		operationTypeRepository.save(ot);
		boolean exists = operationTypeRepository.existsById(999);
		assertTrue(exists);
	}
	
	@Test
	public void notExistsByIdFailTest() {
		boolean exists = operationTypeRepository.existsById(1000);
		assertFalse(exists);
	}

}
