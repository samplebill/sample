package org.mifosplatform.portfolio.billmaster.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.billingmaster.command.BillMasterCommand;

public class BillMasterCommandValidator {



			private final BillMasterCommand command;

			public BillMasterCommandValidator(final BillMasterCommand

					command) {
				this.command=command;
			}


			public void validateForCreate() {
		         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
				DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("billmaster");
				baseDataValidator.reset().parameter("dueDate").value(command.getDueDate()).notBlank().notNull();

				if (!dataValidationErrors.isEmpty()) {
					throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
				}
			}
		}
