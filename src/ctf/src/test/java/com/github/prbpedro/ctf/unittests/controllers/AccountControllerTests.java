package com.github.prbpedro.ctf.unittests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.prbpedro.ctf.controllers.AccountController;
import com.github.prbpedro.ctf.dtos.EntradaAccountSave;
import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.services.IAccountService;
import com.github.prbpedro.ctf.util.Constantes;

@SpringBootTest
public class AccountControllerTests {

	@Autowired
	private AccountController accountController;

	@MockBean
	private IAccountService accountService;
	
	@Test
	void getSucessTest() {
		Account acc = new Account();
		acc.setId(999L);
		acc.setDocumentNumber(999L);

		Mockito.when(accountService.get(1L)).thenReturn(acc);
		ResponseEntity<?> resp = accountController.get(1L);

		assertNotNull(resp);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		assertEquals(resp.getBody().getClass(), Account.class);
		assertEquals(resp.getBody(), acc);
	}
	
	@Test
	void getNotFoundSucessTest() {
		Mockito.when(accountService.get(1L)).thenReturn(null);
		ResponseEntity<?> resp = accountController.get(1L);

		assertNotNull(resp);
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
		assertEquals(resp.getBody().getClass(), GenericOperationResponse.class);
		assertFalse(((GenericOperationResponse)resp.getBody()).isError());
		assertEquals(((GenericOperationResponse)resp.getBody()).getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.NOT_FOUND_BUSCA_ACCOUNT);
		assertEquals(((GenericOperationResponse)resp.getBody()).getEntity(), "1");
	}
	
	@Test
	void getExceptionFailTest() {
		Mockito.when(accountService.get(Mockito.any())).thenThrow(RuntimeException.class);
		ResponseEntity<?> resp = accountController.get(1L);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
		assertEquals(resp.getBody().getClass(), GenericOperationResponse.class);
		assertTrue(((GenericOperationResponse)resp.getBody()).isError());
		assertEquals(((GenericOperationResponse)resp.getBody()).getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.ERRO_BUSCA_ACCOUNT);
		assertEquals(((GenericOperationResponse)resp.getBody()).getEntity(), "1");
	}

	@Test
	void saveExceptionFailTest() {
		Mockito.when(accountService.save(Mockito.any())).thenThrow(RuntimeException.class);

		EntradaAccountSave entrada = new EntradaAccountSave();
		entrada.setDocumentNumber(1L);

		ResponseEntity<GenericOperationResponse> resp = accountController.save(entrada);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
		assertEquals(resp.getBody().getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.ERROR_PERSIST);
		assertTrue(resp.getBody().isError());
		assertEquals(resp.getBody().getEntity().getClass(), Account.class);
		assertEquals(((Account) resp.getBody().getEntity()).getDocumentNumber(), entrada.getDocumentNumber());
		assertNull(((Account) resp.getBody().getEntity()).getId());
	}

	@Test
	void saveSucessTest() {
		Account acc = new Account();
		acc.setId(999L);
		acc.setDocumentNumber(999L);

		ResponseEntity<GenericOperationResponse> opResp = new ResponseEntity<GenericOperationResponse>(
				new GenericOperationResponse(false, Constantes.SUCESS_PERSIST, acc), HttpStatus.OK);

		Mockito.when(accountService.save(Mockito.any())).thenReturn(opResp);

		EntradaAccountSave entrada = new EntradaAccountSave();
		entrada.setDocumentNumber(999L);

		ResponseEntity<GenericOperationResponse> resp = accountController.save(entrada);

		assertEquals(opResp, resp);
	}
}
