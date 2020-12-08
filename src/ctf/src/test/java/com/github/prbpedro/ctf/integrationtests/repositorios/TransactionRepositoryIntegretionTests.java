package com.github.prbpedro.ctf.integrationtests.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.repositorios.TransactionRepository;

@DataJpaTest
public class TransactionRepositoryIntegretionTests {

	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	public void saveSucessTest() {
		Transaction t = new Transaction();
		t.setId(null);
		t.setAccountId(1L);
		t.setAmount(BigDecimal.TEN);
		t.setEventDate(Calendar.getInstance().getTime());
		t.setOperationTypeId(1);
		
		assertNull(t.getId());

		Transaction savedT = transactionRepository.save(t);

		assertNotNull(savedT);
		assertNotNull(savedT.getId());
		assertEquals(savedT, t);
		
		Transaction gotT = transactionRepository.getOne(t.getId());
		
		assertEquals(savedT.getAccountId(), gotT.getAccountId());
		assertEquals(savedT.getOperationTypeId(), gotT.getOperationTypeId());
		assertEquals(savedT.getAmount(), gotT.getAmount());
		assertEquals(savedT.getEventDate(), gotT.getEventDate());
	}

}
