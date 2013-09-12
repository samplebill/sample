package org.mifosplatform.portfolio.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.plan.commands.PlansCommand;


public class PlanCommandValidator {

	private final PlansCommand command;

	public PlanCommandValidator(final PlansCommand command) {
		this.command=command;
	}


	public void validateForCreate() {
         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("plan");
		baseDataValidator.reset().parameter("plan_code").value(command.getPlan_code()).notBlank();
		//baseDataValidator.reset().parameter("subscription_type").value(command.getSubscription_type()).notBlank().notNull();
		baseDataValidator.reset().parameter("startDate").value(command.getStartDate()).notBlank();
		baseDataValidator.reset().parameter("services").value(command.getServices()).notBlank();

		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
		}
	}
}
