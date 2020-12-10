package com.github.prbpedro.ctf.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.repositorios.TransactionRepository;
import com.github.prbpedro.ctf.services.IAccountService;
import com.github.prbpedro.ctf.services.ITransactionService;
import com.github.prbpedro.ctf.util.Constantes;

@Service
public class TransactionService implements ITransactionService {

	private final TransactionRepository transactionRepository;
	
	@Autowired
	private IAccountService accountService;

	public TransactionService(
			TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	@Transactional
	public ResponseEntity<GenericOperationResponse> save(Transaction entity) {
		
		entity.setAccount(accountService.get(entity.getAccount().getId()));
		
		if(!accountService.updateAvaibleCreditLimit(entity)) {
			GenericOperationResponse body = new GenericOperationResponse(false, Constantes.ERROR_PERSIST, entity);
			body.getMessages().put("avaibleCreditLimit", "não existe crédito suficiente.");
			return new ResponseEntity<>(
					body,
					HttpStatus.OK);
		}
		
		entity = transactionRepository.save(entity);

		return new ResponseEntity<>(
				 new GenericOperationResponse(false, Constantes.SUCESS_PERSIST, entity),
				HttpStatus.OK);
	}
	
}
