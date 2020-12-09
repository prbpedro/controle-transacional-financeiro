package com.github.prbpedro.ctf.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.prbpedro.ctf.util.Constantes;

@Entity
@Table(name = Constantes.TRANSACTION_TABLE_NAME)
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constantes.TRANSACTION_COLUMN_ID_NAME)
	private Long id;

	@Column(name = Constantes.TRANSACTION_COLUMN_AMOUNT_NAME, nullable = false)
	private BigDecimal amount;

	@Column(name = Constantes.TRANSACTION_COLUMN_EVENT_DATE_NAME, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date eventDate;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = Constantes.TRANSACTION_COLUMN_ACCOUNT_ID_NAME, nullable = false)
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = Constantes.TRANSACTION_COLUMN_OPERATION_ID_NAME, nullable = false)
	private OperationType operationType;

	public Transaction() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
}
