package com.github.prbpedro.ctf.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class EntradaAccountSave {
	
	@NotNull
	private Long documentNumber;
	
	@NotNull
	private BigDecimal availableCreditLimit;
	
	public EntradaAccountSave() {
		
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
