package com.github.prbpedro.ctf.dtos;

import javax.validation.constraints.NotNull;

public class EntradaAccountSave {
	
	@NotNull
	private Long documentNumber;
	
	public EntradaAccountSave() {
		
	}

	public Long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}
}
