package org.mifosplatform.portfolio.billingcycle.domain.domain;

import org.mifosplatform.portfolio.billingcycle.domain.BillingCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BillingCycleRepository extends
		JpaRepository<BillingCycle, Long>,
		JpaSpecificationExecutor<BillingCycle> {

}
