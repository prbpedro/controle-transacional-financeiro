package com.github.prbpedro.ctf.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.repositorios.AccountRepository;
import com.github.prbpedro.ctf.services.IAccountService;
import com.github.prbpedro.ctf.util.Constantes;

@Component
public class AccountService implements IAccountService {

	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional
	public ResponseEntity<GenericOperationResponse> save(Account entity) {
		boolean existe = accountRepository.existsByDocumentNumber(entity.getDocumentNumber());
		if (existe) {
			return new ResponseEntity<>(
					new GenericOperationResponse(true, Constantes.ERROR_ACCOUNT_DOCUMENT_NUMBER_ALREDY_EXISTS, entity),
					HttpStatus.CONFLICT);
		}

		entity = accountRepository.save(entity);

		return new ResponseEntity<>(
				new GenericOperationResponse(false, Constantes.SUCESS_PERSIST_ACCOUNT, entity),
				HttpStatus.OK);
	}
	
	public Account get(Long id) {
		Optional<Account> entity = accountRepository.findById(id);
		if(entity.isPresent()) {
			return entity.get();
		}else {
			return null;
		}
	}

	@Override
	public boolean exists(Long id) {
		return accountRepository.existsById(id);
	}
}
