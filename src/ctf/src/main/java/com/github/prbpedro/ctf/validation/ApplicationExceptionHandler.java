package com.github.prbpedro.ctf.validation;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.prbpedro.ctf.dtos.GenericOperationResponse;
import com.github.prbpedro.ctf.util.Constantes;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public GenericOperationResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		
		List<FieldError> fieldErrors = result.getFieldErrors();
		GenericOperationResponse resp = processFieldErrors(result.getObjectName(), fieldErrors);
		
		for (ObjectError error : result.getGlobalErrors()) {
			resp.getMessages().put(error.getObjectName(), error.getDefaultMessage());
		}
		
		return resp;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public GenericOperationResponse messageNotReadableException(HttpMessageNotReadableException ex) {
		return new GenericOperationResponse(true, Constantes.ERROR_VALIDACAO_JSON_INVALIDO, null);
	}

	private GenericOperationResponse processFieldErrors(Object target,
			List<FieldError> fieldErrors) {
		GenericOperationResponse resp = new GenericOperationResponse(true, Constantes.ERROR_VALIDACAO, target);
		for (FieldError fieldError : fieldErrors) {
			resp.getMessages().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return resp;
	}
}