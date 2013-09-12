package org.mifosplatform.portfolio.servicemaster.service;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.servicemaster.data.SericeMasterOptionsData;
import org.mifosplatform.portfolio.services.data.ServiceMasterData;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;

public interface ServiceMasterReadPlatformService {
	 Collection<ServiceMasterData> retrieveAllServiceMasterData() ;

	List<SericeMasterOptionsData> retrieveServices();

	SericeMasterOptionsData retrieveIndividualService(Long serviceId);
}
