package com.github.prbpedro.ctf.integrationtests.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.repositorios.AccountRepository;

@DataJpaTest
public class AccountRepositoryIntegretionTests {

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void saveSucessTest() {
		Account acc = new Account();
		acc.setDocumentNumber(1L);
		assertNull(acc.getId());

		Account savedAcc = accountRepository.save(acc);

		assertNotNull(savedAcc);
		assertNotNull(savedAcc.getId());
		assertEquals(acc, savedAcc);
	}

	@Test
	public void existsByDocumentNumberSucessTest() {
		Account acc = new Account();
		acc.setDocumentNumber(2L);
		assertNull(acc.getId());

		Account savedAcc = accountRepository.save(acc);

		assertNotNull(savedAcc);
		assertNotNull(savedAcc.getId());
		assertEquals(acc, savedAcc);

		boolean exists = accountRepository.existsByDocumentNumber(2L);

		assertTrue(exists);
	}

	@Test
	public void notExistsByDocumentNumberSucessTest() {
		boolean exists = accountRepository.existsByDocumentNumber(3L);
		assertFalse(exists);
	}
	
	@Test
	public void notExistsFindByIdSucessTest() {
		Optional<Account> exists = accountRepository.findById(5L);

		assertFalse(exists.isPresent());
	}
	
	@Test
	public void existsFindByIdSucessTest() {
		Account acc = new Account();
		acc.setDocumentNumber(4L);
		assertNull(acc.getId());

		Account savedAcc = accountRepository.save(acc);

		assertNotNull(savedAcc);
		assertNotNull(savedAcc.getId());
		assertEquals(acc, savedAcc);

		Optional<Account> exists = accountRepository.findById(acc.getId());

		assertTrue(exists.isPresent());
	}
	
	@Test
	public void existsByIdSucessTest() {
		Account acc = new Account();
		acc.setDocumentNumber(55L);
		assertNull(acc.getId());

		accountRepository.save(acc);
		
		boolean exists = accountRepository.existsById(acc.getId());
		assertTrue(exists);
	}
	
	@Test
	public void notExistsByIdFailTest() {
		boolean exists = accountRepository.existsById(66L);
		assertFalse(exists);
	}
}
