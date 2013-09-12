package org.mifosplatform.portfolio.servicemaster.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface ServiceMasterRepository extends JpaRepository<ServiceMaster, Long>, JpaSpecificationExecutor<ServiceMaster>{

}
