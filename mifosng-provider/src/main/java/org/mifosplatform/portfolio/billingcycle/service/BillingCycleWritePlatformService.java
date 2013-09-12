package org.mifosplatform.portfolio.billingcycle.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.billingcycle.command.BillingCycleCommand;

public interface BillingCycleWritePlatformService {


		CommandProcessingResult createBillingCycle( BillingCycleCommand command);

	}
