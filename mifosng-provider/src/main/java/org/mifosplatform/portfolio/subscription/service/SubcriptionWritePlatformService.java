package org.mifosplatform.portfolio.subscription.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.subscription.commands.SubscriptionCommand;

public interface SubcriptionWritePlatformService {


	CommandProcessingResult createSubscription(final SubscriptionCommand command);

	CommandProcessingResult updateSubscriptionProduct(SubscriptionCommand command);


	CommandProcessingResult deleteSubscription(Long productId);

}
