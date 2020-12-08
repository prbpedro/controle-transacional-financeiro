package com.github.prbpedro.ctf.controllers;

import java.util.Calendar;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.prbpedro.ctf.dtos.EntradaTransactionSave;
import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.entidades.Transaction;
import com.github.prbpedro.ctf.services.ITransactionService;
import com.github.prbpedro.ctf.util.Constantes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags=Constantes.API_TAG_TRANSACTIONS_CONTROLLER,
		description = Constantes.API_DESCRIPTION_TRANSACTIONS_CONTROLLER)
@RestController
@RequestMapping(Constantes.API_MAPPING_TRANSACTIONS_CONTROLLER)
public class TransactionController {

	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	private final ITransactionService transactionService;

	public TransactionController(ITransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@ApiOperation(value = Constantes.API_DESCRIPTION_OPERATION_SAVE_TRANSACTIONS_CONTROLLER)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = Constantes.SUCESS_PERSIST, response = GenericOperationResponse.class),
			@ApiResponse(code = 400, message = Constantes.ERROR_VALIDACAO, response = GenericOperationResponse.class),
			@ApiResponse(code = 500, message = Constantes.ERROR_PERSIST, response = GenericOperationResponse.class), })
	@PostMapping
	public ResponseEntity<GenericOperationResponse> save(
			@Valid 
			@RequestBody 
			EntradaTransactionSave entrada) {
		
		Transaction entity = new Transaction();
		entity.setAccountId(entrada.getAccountId());
		entity.setOperationTypeId(entrada.getOperationTypeId());
		entity.setAmount(entrada.getAmount());
		entity.setEventDate(Calendar.getInstance().getTime());
		
		try {
			return transactionService.save(entity);
		} catch (Exception e) {
			logger.error(Constantes.ERROR_PERSIST, e);
			return new ResponseEntity<>(
					new GenericOperationResponse(true, Constantes.ERROR_PERSIST, entity),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
