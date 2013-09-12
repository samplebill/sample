package org.mifosplatform.portfolio.plan.service;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.plan.commands.PlansCommand;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;

public interface PlanWritePlatformService {

	CommandProcessingResult createPlan(final PlansCommand command);

	CommandProcessingResult updatePlan(PlansCommand command, PlanData serviceData,
			List<ServiceData> services,Long planId);

	void deletePlan(Long planCode, PlanData serviceData,
			List<ServiceData> services);

}
