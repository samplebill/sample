package org.mifosplatform.portfolio.paymodes.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.paymodes.commands.PaymodeCommand;


public class PaymodeCommandValidator {

	private final PaymodeCommand command;

	public PaymodeCommandValidator(final PaymodeCommand command) {
		this.command=command;
	}


	public void validateForCreate() {
         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("paymode");
		baseDataValidator.reset().parameter("paymode").value(command.getPaymode()).notBlank().notNull();
		baseDataValidator.reset().parameter("escription").value(command.getDescription()).notBlank().notNull();
	//	baseDataValidator.reset().parameter("category").value(command.getCategory()).notBlank().notNull();

		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
		}
	}
}
