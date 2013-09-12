package org.mifosplatform.portfolio.paymodes.domain;


	import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

	public interface PaymodeRepository  extends
	JpaRepository<Paymode, Long>,
	JpaSpecificationExecutor<Paymode>{


	}
