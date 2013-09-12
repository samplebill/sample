package org.mifosplatform.portfolio.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.payterms.commands.PaytermsCommand;

public class PaymentsCommandValidator {


	private final PaytermsCommand command;

	public PaymentsCommandValidator(final PaytermsCommand command) {
		this.command=command;
	}



public void validateForCreate() {

		List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();

		DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("payments");

		baseDataValidator.reset().parameter("payment_period").value(command.getPayterm_period()).notNull();
		baseDataValidator.reset().parameter("payment_type").value(command.getPayterm_type()).notNull();
		baseDataValidator.reset().parameter("units").value(command.getUnits()).notNull();
		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
		}


}
}