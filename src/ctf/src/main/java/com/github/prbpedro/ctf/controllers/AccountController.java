package com.github.prbpedro.ctf.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.prbpedro.ctf.dtos.EntradaAccountSave;
import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Account;
import com.github.prbpedro.ctf.services.IAccountService;
import com.github.prbpedro.ctf.util.Constantes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags=Constantes.API_TAG_ACCOUNTS_CONTROLLER,
		description = Constantes.API_DESCRIPTION_ACCOUNTS_CONTROLLER)
@RestController
@RequestMapping(Constantes.API_MAPPING_ACCOUNTS_CONTROLLER)
public class AccountController {

	private final Logger logger = LoggerFactory.getLogger(AccountController.class);

	private final IAccountService accountService;

	public AccountController(IAccountService accountService) {
		this.accountService = accountService;
	}

	@ApiOperation(value = Constantes.API_DESCRIPTION_OPERATION_SAVE_ACCOUNTS_CONTROLLER)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = Constantes.SUCESS_PERSIST, response = GenericOperationResponse.class),
			@ApiResponse(code = 400, message = Constantes.ERROR_VALIDACAO, response = GenericOperationResponse.class),
			@ApiResponse(code = 409, message = Constantes.ERROR_ACCOUNT_DOCUMENT_NUMBER_ALREDY_EXISTS, response = GenericOperationResponse.class),
			@ApiResponse(code = 500, message = Constantes.ERROR_PERSIST, response = GenericOperationResponse.class), })
	@PostMapping
	public ResponseEntity<GenericOperationResponse> save(
			@Valid 
			@RequestBody 
			EntradaAccountSave entrada) {
		
		Account entity = null;
		try {
			entity = new Account();
			entity.setDocumentNumber(entrada.getDocumentNumber());
			return accountService.save(entity);
		} catch (Exception e) {
			logger.error(Constantes.ERROR_PERSIST, e);
			return new ResponseEntity<>(
					new GenericOperationResponse(true, Constantes.ERROR_PERSIST, entity),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = Constantes.API_DESCRIPTION_OPERATION_GET_ACCOUNTS_CONTROLLER)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = Constantes.SUCESSO_BUSCA_ACCOUNT, response = Account.class),
			@ApiResponse(code = 404, message = Constantes.NOT_FOUND_BUSCA_ACCOUNT, response = GenericOperationResponse.class),
			@ApiResponse(code = 500, message = Constantes.ERRO_BUSCA_ACCOUNT, response = GenericOperationResponse.class), })
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> get(
			@PathVariable 
			Long id) {
		try {
			Account acc = accountService.get(id);
			if(acc==null) {
				return new ResponseEntity<>(
						new GenericOperationResponse(false, Constantes.NOT_FOUND_BUSCA_ACCOUNT, id.toString()),
						HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(
						acc,
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(Constantes.ERRO_BUSCA_ACCOUNT, e);
			return new ResponseEntity<>(
					new GenericOperationResponse(true, Constantes.ERRO_BUSCA_ACCOUNT, id.toString()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
