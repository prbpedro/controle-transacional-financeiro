package com.github.prbpedro.ctf.services.impl;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.repositorios.TransactionRepository;
import com.github.prbpedro.ctf.services.ITransactionService;
import com.github.prbpedro.ctf.util.Constantes;

@Service
public class TransactionService implements ITransactionService {

	private final TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	@Transactional
	public ResponseEntity<GenericOperationResponse> save(Transaction entity) {
		entity = transactionRepository.save(entity);

		return new ResponseEntity<>(
				new GenericOperationResponse(false, Constantes.SUCESS_PERSIST, entity),
				HttpStatus.OK);
	}
	
}
