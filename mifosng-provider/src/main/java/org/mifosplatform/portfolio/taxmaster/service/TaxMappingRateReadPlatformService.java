package org.mifosplatform.portfolio.taxmaster.service;

import java.util.Collection;

import org.mifosplatform.portfolio.charge.data.ChargeCodeData;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterData;

public interface TaxMappingRateReadPlatformService {
	Collection<TaxMasterData> retrieveTaxMappingRateforTemplate() ;
    Collection<ChargeCodeData> retrieveChargeCodeForTemplate();
}
