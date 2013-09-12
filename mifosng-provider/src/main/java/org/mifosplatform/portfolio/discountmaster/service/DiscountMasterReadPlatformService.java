package org.mifosplatform.portfolio.discountmaster.service;

import java.util.Collection;

import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;

public interface DiscountMasterReadPlatformService {
	public Collection<DiscountMasterData> retrieveAllDiscountMasterData();
	public DiscountMasterData retrieveDiscountMasterData(final Long id);
}
