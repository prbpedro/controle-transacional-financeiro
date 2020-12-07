package com.github.prbpedro.ctf.services.impl;

import org.springframework.stereotype.Service;

import com.github.prbpedro.ctf.entidades.OperationType;
import com.github.prbpedro.ctf.repositorios.OperationTypeRepository;
import com.github.prbpedro.ctf.services.IOperationTypeService;
import com.github.prbpedro.ctf.util.Constantes;

@Service
public class OperationTypeService implements IOperationTypeService {

	private final OperationTypeRepository operationTypeRepository;

	public OperationTypeService(OperationTypeRepository operationTypeRepository) {
		this.operationTypeRepository = operationTypeRepository;
	}

	@Override
	public boolean exists(Integer id) {
		return operationTypeRepository.existsById(id);
	}

	@Override
	public void persistDefaultValues() {
		operationTypeRepository.save(new OperationType(Constantes.COMPRA_A_VISTA_ID, Constantes.COMPRA_A_VISTA));
		operationTypeRepository.save(new OperationType(Constantes.COMPRA_PARCELADA_ID, Constantes.COMPRA_PARCELADA));
		operationTypeRepository.save(new OperationType(Constantes.SAQUE_ID, Constantes.SAQUE));
		operationTypeRepository.save(new OperationType(Constantes.PAGAMENTO_ID, Constantes.PAGAMENTO));
	}

}
