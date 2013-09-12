package org.mifosplatform.portfolio.savingsdepositproduct.data;

import org.mifosplatform.portfolio.ticketmaster.domain.TicketMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketMasterRepository  extends
	JpaRepository<TicketMaster, Long>,
	JpaSpecificationExecutor<TicketMaster>{

	}


