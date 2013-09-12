package org.mifosplatform.portfolio.subscription.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

	public interface SubscriptionTypeRepository  extends
	JpaRepository<SubscriptionType, Long>,
	JpaSpecificationExecutor<SubscriptionType>{


	}
