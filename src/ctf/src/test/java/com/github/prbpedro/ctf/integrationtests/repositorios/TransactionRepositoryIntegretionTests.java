package com.github.prbpedro.ctf.integrationtests.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.entidades.OperationType;
import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.repositorios.AccountRepository;
import com.github.prbpedro.ctf.repositorios.OperationTypeRepository;
import com.github.prbpedro.ctf.repositorios.TransactionRepository;

@DataJpaTest
public class TransactionRepositoryIntegretionTests {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private OperationTypeRepository operationTypeRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	public void saveSucessTest() {
		Account acc = new Account();
		acc.setId(1L);
		acc.setDocumentNumber(1L);
		acc = accountRepository.save(acc);
		
		OperationType ot = new OperationType();
		ot.setId(1);
		ot.setDescription("d");
		ot = operationTypeRepository.save(ot);
		
		Transaction t = new Transaction();
		t.setAccount(acc);
		t.setOperationType(ot);
		t.setId(null);
		t.setAmount(BigDecimal.TEN);
		t.setEventDate(Calendar.getInstance().getTime());
		
		assertNull(t.getId());

		Transaction savedT = transactionRepository.save(t);

		assertNotNull(savedT);
		assertNotNull(savedT.getId());
		assertEquals(savedT, t);
		
		Transaction gotT = transactionRepository.getOne(t.getId());
		
		assertEquals(savedT.getAccount().getId(), gotT.getAccount().getId());
		assertEquals(savedT.getOperationType().getId(), gotT.getOperationType().getId());
		assertEquals(savedT.getAmount(), gotT.getAmount());
		assertEquals(savedT.getEventDate(), gotT.getEventDate());
	}

}
