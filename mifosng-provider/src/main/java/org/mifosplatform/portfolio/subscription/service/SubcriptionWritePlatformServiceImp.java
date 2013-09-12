package org.mifosplatform.portfolio.subscription.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.savingsdepositproduct.exception.DepositProductNotFoundException;
import org.mifosplatform.portfolio.subscription.commands.SubscriptionCommand;
import org.mifosplatform.portfolio.subscription.domain.Subscription;
import org.mifosplatform.portfolio.subscription.domain.SubscriptionCommandValidator;
import org.mifosplatform.portfolio.subscription.domain.SubscriptionRepository;
import org.mifosplatform.portfolio.subscription.domain.SubscriptionType;
import org.mifosplatform.portfolio.subscription.domain.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SubcriptionWritePlatformServiceImp implements SubcriptionWritePlatformService{



	private PlatformSecurityContext context;

	private SubscriptionRepository subscriptionRepository;
	private SubscriptionTypeRepository subscriptionTypeRepository;




	@Autowired
	public SubcriptionWritePlatformServiceImp(final PlatformSecurityContext context, final SubscriptionRepository subscription,final SubscriptionTypeRepository subscriptionTypeRepository){
		this.context=context;
		this.subscriptionRepository=subscription;
		this.subscriptionTypeRepository=subscriptionTypeRepository;
	}
	@Transactional
	@Override
	public CommandProcessingResult createSubscription(SubscriptionCommand command) {
		try
		{

			this.context.authenticatedUser();
			SubscriptionCommandValidator validator=new SubscriptionCommandValidator(command);
			validator.validateForCreate();
			//SubscriptionType type=subscriptionTypeRepository.findOne(command.getSubscriptionTypeId());



			Subscription data=new Subscription(command.getSubscription_period(),command.getUnits(),command.getDay_name(),command.getSubscriptionTypeId());



			this.subscriptionRepository.save(data);
			return new CommandProcessingResult(data.getId());
		} catch (DataIntegrityViolationException dve) {
			 handleDataIntegrityIssues(command, dve);
			 return new CommandProcessingResult(Long.valueOf(-1));
		}


	}
	private void handleDataIntegrityIssues(SubscriptionCommand command,
			DataIntegrityViolationException dve) {
		// TODO Auto-generated method stub

	}
	@Transactional
	@Override
	public CommandProcessingResult updateSubscriptionProduct(SubscriptionCommand command) {
		try {
			this.context.authenticatedUser();

			Subscription subscription=this.subscriptionRepository.findOne(command.getId());

			if(subscription==null){
				throw new DepositProductNotFoundException(command.getId());
			}

			subscription.update(command,command.getDay_name());
			this.subscriptionRepository.save(subscription);
			return new CommandProcessingResult(Long.valueOf(subscription.getId()));
		} catch (DataIntegrityViolationException dve) {
			 handleDataIntegrityIssues(command, dve);
			 return new CommandProcessingResult(Long.valueOf(-1));
		}
	}
	@Transactional
	@Override
	public CommandProcessingResult deleteSubscription(Long productId) {

			this.context.authenticatedUser();
			Subscription subscription=this.subscriptionRepository.findOne(productId);
			if(subscription==null){
				throw new DepositProductNotFoundException(productId);
			}
			subscription.delete();
			this.subscriptionRepository.save(subscription);
			return new CommandProcessingResult(Long.valueOf(subscription.getId()));
		}
	}
