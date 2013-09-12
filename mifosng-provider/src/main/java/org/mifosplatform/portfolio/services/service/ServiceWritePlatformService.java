package org.mifosplatform.portfolio.services.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.servicemaster.commands.ServicesCommand;

public interface ServiceWritePlatformService {


	CommandProcessingResult createService(final ServicesCommand command);
}
