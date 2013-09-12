package org.mifosplatform.portfolio.subscription.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubscriptionRepository  extends
JpaRepository<Subscription, Long>,
JpaSpecificationExecutor<Subscription>{


}
