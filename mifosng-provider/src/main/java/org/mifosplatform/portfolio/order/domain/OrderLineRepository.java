package org.mifosplatform.portfolio.order.domain;

import org.mifosplatform.portfolio.order.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderLineRepository extends
JpaRepository<OrderLine, Long>,
JpaSpecificationExecutor<OrderLine>{

}
