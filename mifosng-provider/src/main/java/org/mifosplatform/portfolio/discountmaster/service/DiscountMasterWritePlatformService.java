package org.mifosplatform.portfolio.discountmaster.service;


import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.discountmaster.commands.DiscountMasterCommand;
import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;

public interface DiscountMasterWritePlatformService {

	public Long createDiscountMaster(final DiscountMasterCommand command);
	public Long updateDiscountMaster(final DiscountMasterData data);
	public CommandProcessingResult deleteDiscountMaster(long id);

}
