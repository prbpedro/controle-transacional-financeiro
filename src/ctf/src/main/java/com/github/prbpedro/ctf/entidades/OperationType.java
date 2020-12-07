package com.github.prbpedro.ctf.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.prbpedro.ctf.util.Constantes;

@Entity
@Table(name = Constantes.OPERATION_TYPE_TABLE_NAME)
public class OperationType {

	@Id
	@Column(name = Constantes.OPERATION_TYPE_COLUMN_ID_NAME)
	private Integer id;

	@Column(name = Constantes.OPERATION_TYPE_COLUMN_DESCRIPTION_NAME, nullable = false, unique = true)
	private String description;

	public OperationType() {

	}

	public OperationType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
