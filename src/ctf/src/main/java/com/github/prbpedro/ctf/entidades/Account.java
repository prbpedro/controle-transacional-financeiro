package com.github.prbpedro.ctf.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.prbpedro.ctf.util.Constantes;

@Entity
@Table(name=Constantes.ACCOUNT_TABLE_NAME)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=Constantes.ACCOUNT_COLUMN_ID_NAME)
	private Long id;
	
	@Column(name=Constantes.ACCOUNT_COLUMN_DOCUMENT_NUMBER_NAME, nullable=false, unique=true)
	private Long documentNumber;
	
	@Column(name = Constantes.TRANSACTION_COLUMN_AVAIBLE_CREDIT_LIMIT_NAME, nullable = false)
	private BigDecimal availableCreditLimit;
	
	public Account() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public BigDecimal getAvailableCreditLimit() {
		return availableCreditLimit;
	}

	public void setAvailableCreditLimit(BigDecimal availableCreditLimit) {
		this.availableCreditLimit = availableCreditLimit;
	}
}
