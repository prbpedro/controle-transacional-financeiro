package com.github.prbpedro.ctf.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.prbpedro.ctf.util.Constantes;

@Entity
@Table(name=Constantes.TRANSACTION_TABLE_NAME)
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name=Constantes.TRANSACTION_COLUMN_ID_NAME)
	private Long id;
	
	@Column(name=Constantes.TRANSACTION_COLUMN_ACCOUNT_ID_NAME, nullable=false)
	private Long accountId;
	
	@Column(name=Constantes.TRANSACTION_COLUMN_OPERATION_ID_NAME, nullable=false)
	private Integer operationTypeId;
	
	@Column(name=Constantes.TRANSACTION_COLUMN_AMOUNT_NAME, nullable=false)
	private BigDecimal amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name=Constantes.TRANSACTION_COLUMN_EVENT_DATE_NAME, nullable=false)
	private Date eventDate;
	
	public Transaction() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
