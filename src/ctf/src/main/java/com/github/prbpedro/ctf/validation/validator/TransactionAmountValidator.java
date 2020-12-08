package com.github.prbpedro.ctf.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.prbpedro.ctf.dtos.EntradaTransactionSave;
import com.github.prbpedro.ctf.util.Constantes;
import com.github.prbpedro.ctf.validation.TransactionAmountConstraint;

public class TransactionAmountValidator implements ConstraintValidator<TransactionAmountConstraint, EntradaTransactionSave> {

	@Override
	public void initialize(TransactionAmountConstraint constraint) {
	}

	@Override
	public boolean isValid(EntradaTransactionSave transaction, ConstraintValidatorContext cxt) {

		if ((transaction.getOperationTypeId().equals(Constantes.COMPRA_A_VISTA_ID)
				|| transaction.getOperationTypeId().equals(Constantes.COMPRA_PARCELADA_ID)
				|| transaction.getOperationTypeId().equals(Constantes.SAQUE_ID))
				&& transaction.getAmount().signum() < 0) {
			return true;
		}

		if (transaction.getOperationTypeId().equals(Constantes.PAGAMENTO_ID) && transaction.getAmount().signum() >= 0) {
			return true;
		}

		return false;
	}

}