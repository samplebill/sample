package org.mifosplatform.portfolio.payment.service;

import java.util.List;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;
import org.mifosplatform.portfolio.payment.command.Paymentcommand;

public interface PaymentWritePlatformService {

	CommandProcessingResult createPayment(Paymentcommand command,Long clientId, Long clientBalanceId);

}
