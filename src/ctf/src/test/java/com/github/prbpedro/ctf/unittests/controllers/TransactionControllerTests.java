package com.github.prbpedro.ctf.unittests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.prbpedro.ctf.controllers.TransactionController;
import com.github.prbpedro.ctf.dtos.EntradaTransactionSave;
import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.services.ITransactionService;
import com.github.prbpedro.ctf.util.Constantes;

@SpringBootTest
public class TransactionControllerTests {

	@Autowired
	private TransactionController controller;

	@MockBean
	private ITransactionService service;
	
	@Test
	void saveExceptionFailTest() {
		Mockito.when(service.save(Mockito.any())).thenThrow(RuntimeException.class);

		EntradaTransactionSave entrada = new EntradaTransactionSave();
		entrada.setAccountId(1L);
		entrada.setOperationTypeId(1);
		entrada.setAmount(BigDecimal.ONE);

		ResponseEntity<GenericOperationResponse> resp = controller.save(entrada);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
		assertEquals(resp.getBody().getMessages().get(Constantes.GLOBAL_MESSAGE), Constantes.ERROR_PERSIST);
		assertTrue(resp.getBody().isError());
		assertEquals(resp.getBody().getEntity().getClass(), Transaction.class);
		assertEquals(((Transaction) resp.getBody().getEntity()).getAccountId(), entrada.getAccountId());
		assertEquals(((Transaction) resp.getBody().getEntity()).getOperationTypeId(), entrada.getOperationTypeId());
		assertEquals(((Transaction) resp.getBody().getEntity()).getAmount(), entrada.getAmount());
		assertNotNull(((Transaction) resp.getBody().getEntity()).getEventDate());
		assertNull(((Transaction) resp.getBody().getEntity()).getId());
	}

	@Test
	void saveSucessTest() {
		Transaction t = new Transaction();
		t.setAccountId(1L);
		t.setOperationTypeId(1);
		t.setAmount(BigDecimal.ONE);
		t.setEventDate(Calendar.getInstance().getTime());
		t.setId(1L);

		ResponseEntity<GenericOperationResponse> opResp = new ResponseEntity<GenericOperationResponse>(
				new GenericOperationResponse(false, Constantes.SUCESS_PERSIST, t), HttpStatus.OK);

		Mockito.when(service.save(Mockito.any())).thenReturn(opResp);

		EntradaTransactionSave entrada = new EntradaTransactionSave();
		entrada.setAccountId(1L);
		entrada.setOperationTypeId(1);
		entrada.setAmount(BigDecimal.ONE);

		ResponseEntity<GenericOperationResponse> resp = controller.save(entrada);

		assertEquals(opResp, resp);
	}
}
