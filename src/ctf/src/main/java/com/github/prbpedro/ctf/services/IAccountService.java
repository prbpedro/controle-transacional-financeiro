package com.github.prbpedro.ctf.services;

import org.springframework.http.ResponseEntity;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Account;

public interface IAccountService {
	ResponseEntity<GenericOperationResponse> save(Account entity);
	Account get(Long id);
	boolean exists(Long id);
}
