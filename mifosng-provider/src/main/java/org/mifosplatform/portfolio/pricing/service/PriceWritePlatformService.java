package org.mifosplatform.portfolio.pricing.service;

import java.util.List;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.plan.commands.PlansCommand;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.pricing.commands.PricingCommand;

public interface PriceWritePlatformService {

	//EntityIdentifier createPricing(final PricingCommand command);

	CommandProcessingResult createPricing(PricingCommand command,
			List<ServiceData> serviceData,Long planId);

	CommandProcessingResult updatePrice(PricingCommand command, Long priceId);

	void deletePrice(Long priceId);


}
