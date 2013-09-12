package org.mifosplatform.portfolio.plan.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceDetailsRepository  extends
JpaRepository<ServiceDetails, Long>,
JpaSpecificationExecutor<ServiceDetails>{

}
