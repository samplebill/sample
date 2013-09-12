package org.mifosplatform.portfolio.payment.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.portfolio.payment.command.Paymentcommand;

public class PaymentValidator {

	private final Paymentcommand command;

	public PaymentValidator(final  Paymentcommand command) {
		this.command=command;
	}


	public void validateForCreate() {
         List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("payment");
		baseDataValidator.reset().parameter("payment_code").value(command.getPaymentCode()).notBlank().notNull();
		//baseDataValidator.reset().parameter("subscription_type").value(command.getSubscription_type()).notBlank().notNull();
		baseDataValidator.reset().parameter("payment_date").value(command.getPaymentDate()).notBlank().notNull();
		baseDataValidator.reset().parameter("amount_paid").value(command.getAmountPaid()).notBlank().zeroOrPositiveAmount();

		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.", dataValidationErrors);
		}
	}

}
