package org.mifosplatform.portfolio.servicemaster.service;

import java.util.List;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.servicemaster.commands.ServiceMasterCommand;
import org.mifosplatform.portfolio.servicemaster.domain.ServiceMaster;
import org.mifosplatform.portfolio.servicemaster.domain.ServiceMasterCommandValidator;
import org.mifosplatform.portfolio.servicemaster.domain.ServiceMasterRepository;
import org.mifosplatform.portfolio.servicemaster.exceptions.ServiceCodeExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ServiceMasterWritePlatformServiceImpl  implements ServiceMasterWritePlatformService{

	private final PlatformSecurityContext context;
	private final ServiceMasterRepository serviceMasterRepository;

@Autowired
 public ServiceMasterWritePlatformServiceImpl(final PlatformSecurityContext context,final ServiceMasterRepository serviceMasterRepository)
{
	this.context=context;
	this.serviceMasterRepository=serviceMasterRepository;
}
    @Transactional
	@Override
	public CommandProcessingResult createServiceMaster(ServiceMasterCommand command) {
		// TODO Auto-generated method stub
		try {
			context.authenticatedUser();

			this.context.authenticatedUser();
			ServiceMasterCommandValidator validator=new ServiceMasterCommandValidator(command);
			validator.validateForCreate();

			List<ServiceMaster> availService=this.serviceMasterRepository.findAll();
			for(ServiceMaster master:availService)
			{
				String mserCode=master.getServiceCode();
				String cmServCode=command.getServiceCode();
				if(mserCode.equalsIgnoreCase(cmServCode)){
					if(master.getIsDeleted().equalsIgnoreCase("n"))
				{
					throw new ServiceCodeExist(command.getServiceCode());
				}
			}
			}
			ServiceMaster serviceMaster = ServiceMaster.create(command.getServiceCode(),command.getServiceDescription(),command.getServiceType());

			this.serviceMasterRepository.save(serviceMaster);

			return new CommandProcessingResult(serviceMaster.getId());

		} catch (DataIntegrityViolationException dve) {
			return new CommandProcessingResult(Long.valueOf(-1));
		}
	}
	@Override
	public CommandProcessingResult updateService(ServiceMasterCommand command,Long id) {
		try
		{
		ServiceMaster master=this.serviceMasterRepository.findOne(id);

		master.update(command);
		this.serviceMasterRepository.save(master);


		return new CommandProcessingResult(master.getId());
	} catch (DataIntegrityViolationException dve) {
		return new CommandProcessingResult(Long.valueOf(-1));
	}
	}
	@Override
	public void deleteSubscription(Long serviceId) {
		ServiceMaster master=this.serviceMasterRepository.findOne(serviceId);
		master.delete();
		this.serviceMasterRepository.save(master);

	}



}
