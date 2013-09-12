package org.mifosplatform.portfolio.order.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.order.command.OrdersCommand;


public class OrderCommanValidator {

	private final OrdersCommand command;

	public OrderCommanValidator(final OrdersCommand command) {
		this.command=command;
	}


	public void validateForCreate() {
         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("plan");
		baseDataValidator.reset().parameter("planCode").value(command.getPlanid()).notBlank();
		//baseDataValidator.reset().parameter("subscription_type").value(command.getSubscription_type()).notBlank().notNull();
		baseDataValidator.reset().parameter("start_date").value(command.getStartDate()).notBlank();
		baseDataValidator.reset().parameter("paytermCode").value(2).notBlank();

		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
		}
	}
}