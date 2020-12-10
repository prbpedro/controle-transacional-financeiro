package com.github.prbpedro.ctf.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.entidades.OperationType;
import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.repositorios.AccountRepository;
import com.github.prbpedro.ctf.repositorios.TransactionRepository;
import com.github.prbpedro.ctf.services.ITransactionService;
import com.github.prbpedro.ctf.util.Constantes;

@SpringBootTest
public class TransactionServiceUnitTests {
	
	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private TransactionRepository repository;
	
	@Autowired
	private ITransactionService service;
	
	@Test
	public void saveSucessTest() {
		Account acc = new Account();
		acc.setId(1L);
		acc.setAvailableCreditLimit(BigDecimal.ONE);
		acc.setDocumentNumber(1L);
		
		OperationType ot = new OperationType();
		ot.setId(1);
		
		Transaction t = new Transaction();
		t.setAccount(acc);
		t.setOperationType(ot);
		t.setAmount(BigDecimal.ONE);
		t.setEventDate(Calendar.getInstance().getTime());
		t.setId(1L);
		
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.of(acc));
		Mockito.when(accountRepository.getOne(Mockito.any())).thenReturn(acc);
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(t);

		ResponseEntity<GenericOperationResponse> resp = service.save(t);

		assertEquals(HttpStatus.OK, resp.getStatusCode());
		assertEquals(resp.getBody().getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.SUCESS_PERSIST);
		assertEquals(resp.getBody().getEntity(), t);
		assertFalse(resp.getBody().isError());
	}
	
	@Test
	public void saveAvaibleCreditLimitFailTest() {
		Account acc = new Account();
		acc.setId(1L);
		acc.setAvailableCreditLimit(BigDecimal.ONE);
		acc.setDocumentNumber(1L);
		
		OperationType ot = new OperationType();
		ot.setId(1);
		
		Transaction t = new Transaction();
		t.setAccount(acc);
		t.setOperationType(ot);
		t.setAmount(BigDecimal.valueOf(-2L));
		t.setEventDate(Calendar.getInstance().getTime());
		t.setId(1L);
		
		Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.of(acc));
		Mockito.when(accountRepository.getOne(Mockito.any())).thenReturn(acc);
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(t);

		ResponseEntity<GenericOperationResponse> resp = service.save(t);

		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		assertEquals(resp.getBody().getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.ERROR_PERSIST);
		assertEquals(resp.getBody().getMessages().get(Constantes.AVAIBLE_CREDIT_LIMIT), Constantes.NAO_EXISTE_CREDITO_SUFICIENTE);
		assertEquals(resp.getBody().getEntity(), t);
		assertTrue(resp.getBody().isError());
	}

	@Test
	public void saveExceptionTest() {
		Mockito.when(repository.save(Mockito.any())).thenThrow(new RuntimeException());

		Account acc = new Account();
		acc.setId(1L);
		
		OperationType ot = new OperationType();
		ot.setId(1);
		
		Transaction t = new Transaction();
		t.setAccount(acc);
		t.setOperationType(ot);
		t.setAmount(BigDecimal.ONE);
		t.setEventDate(Calendar.getInstance().getTime());
		t.setId(1L);
		assertThrows(RuntimeException.class, () -> {
			service.save(t);
		});
	}
}
