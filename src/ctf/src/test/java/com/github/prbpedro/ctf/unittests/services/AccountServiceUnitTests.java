package com.github.prbpedro.ctf.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.github.prbpedro.ctf.repositorios.AccountRepository;
import com.github.prbpedro.ctf.services.IAccountService;
import com.github.prbpedro.ctf.util.Constantes;

@SpringBootTest
public class AccountServiceUnitTests {

	@Autowired
	private IAccountService accountService;

	@MockBean
	private AccountRepository accountRepository;
	
	@Test
	public void getSucessTest() {		
		Account acc = new Account();
		acc.setDocumentNumber(1L);
		acc.setId(1L);
		
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(acc));

		Account resp = accountService.get(1L);

		assertEquals(acc, resp);
	}
	
	@Test
	public void getNotFoundSucessTest() {		
		Account acc = new Account();
		acc.setDocumentNumber(1L);
		acc.setId(1L);
		
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());

		Account resp = accountService.get(1L);

		assertNull(resp);
	}
	
	@Test
	public void getExceptionTest() {
		Mockito.when(accountRepository.findById(1L)).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, () -> {
			accountService.get(1L);
		});
	}

	@Test
	public void saveSucessTest() {
		Mockito.when(accountRepository.existsByDocumentNumber(1L)).thenReturn(false);

		Account acc = new Account();
		acc.setDocumentNumber(1L);
		acc.setId(1L);

		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(acc);

		ResponseEntity<GenericOperationResponse> resp = accountService.save(acc);

		assertEquals(HttpStatus.OK, resp.getStatusCode());
		assertEquals(resp.getBody().getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.SUCESS_PERSIST);
		assertEquals(resp.getBody().getEntity(), acc);
		assertFalse(resp.getBody().isError());
	}

	@Test
	public void saveAlredyExistsDocumentNumberSucessTest() {
		Mockito.when(accountRepository.existsByDocumentNumber(1L)).thenReturn(true);

		Account acc = new Account();
		acc.setDocumentNumber(1L);
		acc.setId(1L);

		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(acc);

		ResponseEntity<GenericOperationResponse> resp = accountService.save(acc);

		assertEquals(HttpStatus.CONFLICT, resp.getStatusCode());
		assertEquals(resp.getBody().getMessages().get(Constantes.GLOBAL_MESSAGE),
				Constantes.ERROR_ACCOUNT_DOCUMENT_NUMBER_ALREDY_EXISTS);
		assertEquals(resp.getBody().getEntity(), acc);
		assertTrue(resp.getBody().isError());
	}

	@Test
	public void saveExceptionTest() {
		Mockito.when(accountRepository.existsByDocumentNumber(1L)).thenThrow(new RuntimeException());

		Account acc = new Account();
		acc.setDocumentNumber(1L);
		acc.setId(1L);
		assertThrows(RuntimeException.class, () -> {
			accountService.save(acc);
		});
	}
	
	@Test
	public void existsSucessTest() {		
		Mockito.when(accountRepository.existsById(1L)).thenReturn(true);
		boolean resp = accountService.exists(1L);
		assertTrue(resp);
	}
	
	@Test
	public void existsFailTest() {		
		Mockito.when(accountRepository.existsById(1L)).thenReturn(false);
		boolean resp = accountService.exists(1L);
		assertFalse(resp);
	}
	
	@Test
	public void existsExceptionTest() {
		Mockito.when(accountRepository.existsById(1L)).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, () -> {
			accountService.exists(1L);
		});
	}
}
