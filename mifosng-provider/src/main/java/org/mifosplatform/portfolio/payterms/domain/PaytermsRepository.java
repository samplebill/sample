package org.mifosplatform.portfolio.payterms.domain;
	import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

	public interface PaytermsRepository  extends

	JpaRepository<payterms, Long>,
	JpaSpecificationExecutor<payterms>{


	}
