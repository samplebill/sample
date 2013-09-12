package org.mifosplatform.portfolio.chargecode.domain;

import org.mifosplatform.portfolio.charge.service.ChargeCode;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChargeCodeRepository extends JpaRepository<ChargeCode, Long>,
JpaSpecificationExecutor<TaxMaster>{

}
