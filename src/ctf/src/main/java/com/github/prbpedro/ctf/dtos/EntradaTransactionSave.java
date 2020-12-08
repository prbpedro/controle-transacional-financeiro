package com.github.prbpedro.ctf.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.github.prbpedro.ctf.validation.TransactionAccountIdConstraint;
import com.github.prbpedro.ctf.validation.TransactionAmountConstraint;
import com.github.prbpedro.ctf.validation.TransactionOperationTypeIdConstraint;

@TransactionAmountConstraint
public class EntradaTransactionSave {
	
	@NotNull
	@TransactionAccountIdConstraint
	private Long accountId;
	
	@NotNull
	@TransactionOperationTypeIdConstraint
	private Integer operationTypeId;
	
	@NotNull
	private BigDecimal amount;
	
	public EntradaTransactionSave() {
		
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(Integer operationTypeId) {
		this.operationTypeId = operationTypeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
