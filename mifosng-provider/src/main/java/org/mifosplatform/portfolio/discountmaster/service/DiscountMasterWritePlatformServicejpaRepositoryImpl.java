package org.mifosplatform.portfolio.discountmaster.service;


import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.discountmaster.commands.DiscountMasterCommand;
import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;
import org.mifosplatform.portfolio.discountmaster.domain.DiscountMaster;
import org.mifosplatform.portfolio.discountmaster.domain.DiscountMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class DiscountMasterWritePlatformServicejpaRepositoryImpl  implements DiscountMasterWritePlatformService {

		private final PlatformSecurityContext context;
		private final DiscountMasterRepository discountMasterRepository;

	@Autowired
	 public DiscountMasterWritePlatformServicejpaRepositoryImpl(final PlatformSecurityContext context,final DiscountMasterRepository discountMasterRepository)
	{
		this.context=context;
		this.discountMasterRepository=discountMasterRepository;
	}
	@Transactional
	@Override
	public Long createDiscountMaster(DiscountMasterCommand command) {
		// TODO Auto-generated method stub

		try {
			context.authenticatedUser();

			DiscountMaster discountmaster;

		     discountmaster = DiscountMaster.create(command.getDiscountCode(),command.getDiscountDescription(),command.getDiscounType(),command.getDiscountValue());

			this.discountMasterRepository.saveAndFlush(discountmaster);

			return discountmaster.getId();

		} catch (DataIntegrityViolationException dve) {
			 return Long.valueOf(-1);
		}


	}


		@Transactional
		@Override
		public Long updateDiscountMaster(DiscountMasterData data)
		{
			try {
				context.authenticatedUser();

				 long discountMasterId=data.getId();

				DiscountMaster discountmaster=this.discountMasterRepository.findOne(discountMasterId);

				if(discountmaster==null)
				{

				}


				this.discountMasterRepository.saveAndFlush(discountmaster);

				return discountmaster.getId();

			} catch (DataIntegrityViolationException dve) {
				 return Long.valueOf(-1);
			}

		}

		@Transactional
		@Override
		public CommandProcessingResult deleteDiscountMaster(long id)
		{
				context.authenticatedUser();

				DiscountMaster discountmaster=this.discountMasterRepository.findOne(id);

				if(discountmaster==null)
				{

				}
				this.discountMasterRepository.delete(discountmaster);

				return new CommandProcessingResult(discountmaster.getId());
			}


		}
