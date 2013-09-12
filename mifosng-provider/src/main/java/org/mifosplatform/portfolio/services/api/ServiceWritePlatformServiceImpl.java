package org.mifosplatform.portfolio.services.api;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.plan.domain.ServiceRepository;
import org.mifosplatform.portfolio.plan.domain.ServiceType;
import org.mifosplatform.portfolio.servicemaster.commands.ServicesCommand;
import org.mifosplatform.portfolio.services.service.ServiceWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceWritePlatformServiceImpl implements ServiceWritePlatformService {


	private PlatformSecurityContext context;
	private ServiceRepository serviceRepository;

	@Autowired
	public ServiceWritePlatformServiceImpl(final PlatformSecurityContext context,
			final ServiceRepository serviceRepository) {
		this.context = context;
		this.serviceRepository=serviceRepository;

	}


 @Transactional
	@Override
	public CommandProcessingResult createService(ServicesCommand command) {
		try
		{
			this.context.authenticatedUser();
			ServiceType data=new ServiceType(command.getServiceCode(),command.getSerrviceDescription(),command.getServiceType());
			this.serviceRepository.save(data);

			return new CommandProcessingResult(data.getId());

		} catch (DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}

	}


private void handleDataIntegrityIssues(ServicesCommand command,
		DataIntegrityViolationException dve) {
	// TODO Auto-generated method stub

}

}
