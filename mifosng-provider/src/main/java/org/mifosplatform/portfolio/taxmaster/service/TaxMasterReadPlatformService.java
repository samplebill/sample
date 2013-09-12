package org.mifosplatform.portfolio.taxmaster.service;

import java.util.Collection;

import org.mifosplatform.portfolio.taxmaster.data.TaxMasterData;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterDataOptions;


public interface TaxMasterReadPlatformService {
	public Collection<TaxMasterData> retrieveAllTaxMasterData();

	public Collection<TaxMasterDataOptions> retrieveAllTaxes();

}
