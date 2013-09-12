package org.mifosplatform.portfolio.billingcycle.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingcycle.command.BillingCycleCommand;
import org.mifosplatform.portfolio.billingcycle.domain.BillingCycle;
import org.mifosplatform.portfolio.billingcycle.domain.domain.BillingCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillingCycleWritePlatformServiceImp implements
		BillingCycleWritePlatformService {

	private PlatformSecurityContext context;

	private BillingCycleRepository billingCycleRepository;

	@Autowired
	public BillingCycleWritePlatformServiceImp(
			final PlatformSecurityContext context,
			final BillingCycleRepository BillingCycle) {
		this.context = context;
		this.billingCycleRepository = BillingCycle;

	}

	@Transactional
	@Override
	public CommandProcessingResult createBillingCycle(BillingCycleCommand command) {
		try {

			this.context.authenticatedUser();

			String[] services=command.getEvery();

			if(services[1].contains("-1"))
			{
				BillingCycle data = new BillingCycle(command.getBilling_code(),
						command.getDescription(),
						command.getFrequency(),
						services[0]);
				this.billingCycleRepository.save(data);
				return new CommandProcessingResult(data.getId());
			}
			else
			{

			BillingCycle data = new BillingCycle(command.getBilling_code(),
					command.getDescription(),
					command.getFrequency(),
					services[0]);
			this.billingCycleRepository.save(data);
			return new CommandProcessingResult(data.getId());
			}





		} catch (DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}

	}

	private void handleDataIntegrityIssues(BillingCycleCommand command,
			DataIntegrityViolationException dve) {
		// TODO Auto-generated method stub

	}

}