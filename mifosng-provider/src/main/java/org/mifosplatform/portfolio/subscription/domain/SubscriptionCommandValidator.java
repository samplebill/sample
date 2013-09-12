package org.mifosplatform.portfolio.subscription.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.subscription.commands.SubscriptionCommand;


public class SubscriptionCommandValidator {

	private final SubscriptionCommand command;

	public SubscriptionCommandValidator(final SubscriptionCommand command) {
		this.command=command;
	}


	public void validateForCreate() {
         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("subscription");
		baseDataValidator.reset().parameter("subscription_period").value(command.getSubscription_period()).notBlank().notNull();
		//baseDataValidator.reset().parameter("subscription_type").value(command.getSubscription_type()).notBlank().notNull();
		baseDataValidator.reset().parameter("units").value(command.getUnits()).notBlank().notNull();
		baseDataValidator.reset().parameter("day_name").value(command.getDay_name());

		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
		}
	}
}
