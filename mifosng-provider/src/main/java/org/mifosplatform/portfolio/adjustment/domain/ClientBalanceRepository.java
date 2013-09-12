package org.mifosplatform.portfolio.adjustment.domain;

import org.mifosplatform.portfolio.adjustment.domain.ClientBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientBalanceRepository extends JpaRepository<ClientBalance, Long>, JpaSpecificationExecutor<ClientBalance>{

}
