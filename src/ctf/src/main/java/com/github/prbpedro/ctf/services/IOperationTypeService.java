package com.github.prbpedro.ctf.services;

public interface IOperationTypeService {
	boolean exists(Integer id);
	void persistDefaultValues();
}
