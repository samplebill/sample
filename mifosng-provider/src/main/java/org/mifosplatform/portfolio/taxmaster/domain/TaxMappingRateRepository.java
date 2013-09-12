package org.mifosplatform.portfolio.taxmaster.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaxMappingRateRepository extends JpaRepository<TaxMappingRate, Long>,
JpaSpecificationExecutor<TaxMappingRate> {

}
