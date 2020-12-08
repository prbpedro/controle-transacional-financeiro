package com.github.prbpedro.ctf.services;

import org.springframework.http.ResponseEntity;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Transaction;

public interface ITransactionService {
	ResponseEntity<GenericOperationResponse> save(Transaction entity);
}
