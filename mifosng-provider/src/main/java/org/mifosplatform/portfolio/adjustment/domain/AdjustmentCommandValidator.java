package org.mifosplatform.portfolio.adjustment.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.adjustment.commands.AdjustmentCommand;

public class AdjustmentCommandValidator {


	private final AdjustmentCommand command;

			public AdjustmentCommandValidator(final AdjustmentCommand
					command) {
				this.command=command;
			}


			public void validateForCreate() {
		         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
				DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("adjustment");
				baseDataValidator.reset().parameter("adjustment_date").value(command.getAdjustment_date()).notBlank().notNull();
				baseDataValidator.reset().parameter("adjustment_code").value(command.getAdjustment_code()).notBlank().notNull();
				baseDataValidator.reset().parameter("adjustment_type").value(command.getAdjustment_type()).notBlank().notNull();
				baseDataValidator.reset().parameter("amount_paid").value(command.getAmount_paid()).notBlank().notNull();
				if (!dataValidationErrors.isEmpty()) {

					throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
				}
			}
		}
