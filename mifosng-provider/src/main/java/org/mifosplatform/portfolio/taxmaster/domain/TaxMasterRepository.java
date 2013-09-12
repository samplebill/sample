package org.mifosplatform.portfolio.taxmaster.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaxMasterRepository extends JpaRepository<TaxMaster, Long>,
JpaSpecificationExecutor<TaxMaster> {
// no added behaviour


}
