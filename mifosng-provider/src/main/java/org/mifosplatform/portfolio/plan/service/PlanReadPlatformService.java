package org.mifosplatform.portfolio.plan.service;

import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.billingorder.data.BillRuleData;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;

public interface PlanReadPlatformService {
	List<ServiceData> retrieveAllServices();
	List<PlanData> retrievePlanData();
	List<SubscriptionData> retrieveSubscriptionData();
	List<EnumOptionData> retrieveNewStatus();
	List<ServiceData> getselectedService(List<ServiceData> data,List<ServiceData> services);
	List<BillRuleData> retrievebillRules();
	PlanData retrievePlanData(Long planCode);



}
