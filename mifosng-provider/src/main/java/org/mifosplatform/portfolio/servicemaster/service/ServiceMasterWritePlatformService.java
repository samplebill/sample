package org.mifosplatform.portfolio.servicemaster.service;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.servicemaster.commands.ServiceMasterCommand;
import org.mifosplatform.portfolio.servicemaster.data.SericeMasterOptionsData;

public interface ServiceMasterWritePlatformService {
	public CommandProcessingResult createServiceMaster(final ServiceMasterCommand command);

	public CommandProcessingResult updateService(ServiceMasterCommand command, Long serviceId);

	public void deleteSubscription(Long serviceId);

}
