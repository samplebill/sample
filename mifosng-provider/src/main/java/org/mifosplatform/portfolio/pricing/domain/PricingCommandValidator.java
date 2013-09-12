package org.mifosplatform.portfolio.pricing.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.pricing.commands.PricingCommand;

public class PricingCommandValidator {

private final PricingCommand command;

		public PricingCommandValidator(final PricingCommand command) {
			this.command=command;
		}


		public void validateForCreate() {
	         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
			DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("price");
			baseDataValidator.reset().parameter("charge_code").value(command.getChargeCode()).notBlank().notNull();
			baseDataValidator.reset().parameter("discount_id").value(command.getDiscount_id()).notBlank().notNull();
			baseDataValidator.reset().parameter("chargevariant").value(command.getChargingVariant()).notBlank().notNull();
			baseDataValidator.reset().parameter("price").value(command.getPrice()).notBlank().notNull();

			if (!dataValidationErrors.isEmpty()) {
				throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
			}
		}
	}
