package org.mifosplatform.portfolio.payterms.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.payterms.commands.PaytermsCommand;

public interface PaytermsWritePlatformService {

	CommandProcessingResult createPayterm(final PaytermsCommand command);

}
