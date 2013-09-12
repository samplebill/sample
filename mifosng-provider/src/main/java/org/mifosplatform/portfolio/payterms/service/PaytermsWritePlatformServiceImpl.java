package org.mifosplatform.portfolio.payterms.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.payment.service.PaymentsCommandValidator;
import org.mifosplatform.portfolio.payterms.commands.PaytermsCommand;
import org.mifosplatform.portfolio.payterms.domain.PaytermsRepository;
import org.mifosplatform.portfolio.payterms.domain.payterms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PaytermsWritePlatformServiceImpl implements PaytermsWritePlatformService{

	private  PaytermsRepository paytermsRepository;

	private PlatformSecurityContext context;






	@Autowired
	public PaytermsWritePlatformServiceImpl(final PlatformSecurityContext context, final PaytermsRepository payterms){
		this.context=context;
		this.paytermsRepository=payterms;
	}

	@Transactional
	@Override
	public CommandProcessingResult createPayterm(PaytermsCommand command) {
		try
		{

			this.context.authenticatedUser();

			//this.context.authenticatedUser();
			PaymentsCommandValidator validator=new PaymentsCommandValidator(command);
			validator.validateForCreate();

			payterms data=new payterms(command.getPayterm_period(),command.getUnits(),command.getPayterm_type());
			this.paytermsRepository.save(data);

			return new CommandProcessingResult(data.getId());

		} catch (DataIntegrityViolationException dve) {
			 handleDataIntegrityIssues(command, dve);
			 return new CommandProcessingResult(Long.valueOf(-1));
		}

	}

	private void handleDataIntegrityIssues(PaytermsCommand command,
			DataIntegrityViolationException dve) {
		// TODO Auto-generated method stub

	}
}
