package org.mifosplatform.portfolio.billmaster.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BillMasterRepository extends  JpaRepository<BillMaster, Long>,
JpaSpecificationExecutor<BillMaster>{

}
