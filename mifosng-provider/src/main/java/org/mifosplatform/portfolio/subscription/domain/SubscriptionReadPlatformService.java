package org.mifosplatform.portfolio.subscription.domain;

import java.util.Collection;

import org.mifosplatform.portfolio.subscription.data.SubscriptionData;

public interface SubscriptionReadPlatformService {

	Collection<SubscriptionData> retrieveAllSubscription();

	Collection<SubscriptionData> retrieveSubscriptionDetails();

	SubscriptionData retrieveSubscriptionData(Long subscriptionId);

}
