package org.mifosplatform.portfolio.plan.domain;

import org.mifosplatform.portfolio.plan.domain.ServiceDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceDescriptionRepository extends
JpaRepository<ServiceDescription, Long>,
JpaSpecificationExecutor<ServiceDescription>{

}
