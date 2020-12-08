package com.github.prbpedro.ctf.unittests.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.prbpedro.ctf.dtos.EntradaTransactionSave;
import com.github.prbpedro.ctf.util.Constantes;
import com.github.prbpedro.ctf.validation.validator.TransactionAmountValidator;

@SpringBootTest
public class TransactionAmountValidatorTests {

	private TransactionAmountValidator validator = new TransactionAmountValidator();
	
	@Test
	void transactionAmountValidatorCompraAVistaSucessTest() {
		validator.initialize(null);
		
		EntradaTransactionSave transaction = new EntradaTransactionSave();
		transaction.setOperationTypeId(Constantes.COMPRA_A_VISTA_ID);
		transaction.setAmount(BigDecimal.valueOf(-1L));
		
		boolean valid = validator.isValid(transaction, null);
		
		assertTrue(valid);
	}
	
	@Test
	void transactionAmountValidatorCompraParceladaSucessTest() {
		validator.initialize(null);
		
		EntradaTransactionSave transaction = new EntradaTransactionSave();
		transaction.setOperationTypeId(Constantes.COMPRA_PARCELADA_ID);
		transaction.setAmount(BigDecimal.valueOf(-1L));
		
		boolean valid = validator.isValid(transaction, null);
		
		assertTrue(valid);
	}

	@Test
	void transactionAmountValidatorSaqueSucessTest() {
		validator.initialize(null);
		
		EntradaTransactionSave transaction = new EntradaTransactionSave();
		transaction.setOperationTypeId(Constantes.SAQUE_ID);
		transaction.setAmount(BigDecimal.valueOf(-1L));
		
		boolean valid = validator.isValid(transaction, null);
		
		assertTrue(valid);
	}
	
	@Test
	void transactionAmountValidatorSaqueFailTest() {
		validator.initialize(null);
		
		EntradaTransactionSave transaction = new EntradaTransactionSave();
		transaction.setOperationTypeId(Constantes.SAQUE_ID);
		transaction.setAmount(BigDecimal.valueOf(1L));
		
		boolean valid = validator.isValid(transaction, null);
		
		assertFalse(valid);
	}

	@Test
	void transactionAmountValidatorPagamentoSucessTest() {
		validator.initialize(null);
		
		EntradaTransactionSave transaction = new EntradaTransactionSave();
		transaction.setOperationTypeId(Constantes.PAGAMENTO_ID);
		transaction.setAmount(BigDecimal.valueOf(1L));
		
		boolean valid = validator.isValid(transaction, null);
		
		assertTrue(valid);
	}

	@Test
	void transactionAmountValidatorPagamentoFailTest() {
		validator.initialize(null);
		
		EntradaTransactionSave transaction = new EntradaTransactionSave();
		transaction.setOperationTypeId(Constantes.PAGAMENTO_ID);
		transaction.setAmount(BigDecimal.valueOf(-1L));
		
		boolean valid = validator.isValid(transaction, null);
		
		assertFalse(valid);
	}
}
