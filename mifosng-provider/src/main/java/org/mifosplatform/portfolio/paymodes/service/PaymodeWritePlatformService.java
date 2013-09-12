package org.mifosplatform.portfolio.paymodes.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.paymodes.commands.PaymodeCommand;

public interface PaymodeWritePlatformService {
	CommandProcessingResult createPaymode(final PaymodeCommand command);

	CommandProcessingResult updatePaymode(PaymodeCommand command, Long paymodeId);

	void deletepaymode(Long paymodeId);

}
