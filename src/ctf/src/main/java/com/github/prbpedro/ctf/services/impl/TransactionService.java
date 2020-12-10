package com.github.prbpedro.ctf.services.impl;

import javax.transaction.Transactional;

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

	private IAccountService accountService;

	public TransactionService(IAccountService accountService, TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
		this.accountService = accountService;
	}

	@Override
	@Transactional
	public ResponseEntity<GenericOperationResponse> save(Transaction entity) {

		entity.setAccount(accountService.get(entity.getAccount().getId()));

		if (!accountService.updateAvaibleCreditLimit(entity.getAccount().getId(), entity.getAmount())) {
			GenericOperationResponse body = new GenericOperationResponse(true, Constantes.ERROR_PERSIST, entity);
			body.getMessages().put(Constantes.AVAIBLE_CREDIT_LIMIT, Constantes.NAO_EXISTE_CREDITO_SUFICIENTE);
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}

		entity = transactionRepository.save(entity);

		return new ResponseEntity<>(new GenericOperationResponse(false, Constantes.SUCESS_PERSIST, entity),
				HttpStatus.OK);
	}

}
