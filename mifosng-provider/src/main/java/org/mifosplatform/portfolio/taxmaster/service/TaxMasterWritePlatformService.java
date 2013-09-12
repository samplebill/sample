package org.mifosplatform.portfolio.taxmaster.service;

import org.mifosplatform.portfolio.taxmaster.commands.TaxMasterCommand;


public interface TaxMasterWritePlatformService {
	public Long createtaxMaster(final TaxMasterCommand command);
}
