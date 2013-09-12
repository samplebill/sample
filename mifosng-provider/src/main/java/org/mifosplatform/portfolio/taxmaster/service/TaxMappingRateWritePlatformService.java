package org.mifosplatform.portfolio.taxmaster.service;

import org.mifosplatform.portfolio.taxmaster.commands.TaxMappingRateCommand;

public interface TaxMappingRateWritePlatformService {
	public Long createtaxMasterMapping(final TaxMappingRateCommand command);
}
