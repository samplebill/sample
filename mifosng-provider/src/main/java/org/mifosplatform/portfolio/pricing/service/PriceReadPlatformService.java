package org.mifosplatform.portfolio.pricing.service;

import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.charge.data.ChargeData;
import org.mifosplatform.portfolio.charge.data.ChargesData;
import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.pricing.data.PricingData;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;

public interface PriceReadPlatformService {

	 List<ChargesData> retrieveChargeCode();
     List<EnumOptionData> retrieveChargeVariantData();
	List<DiscountMasterData> retrieveDiscountDetails();
	List<PlanData> retrievePlanDetails();
	List<SubscriptionData> retrievePaytermData();
	List<ServiceData> retrievePriceDetails(String planCode);
	List<ServiceData> retrievePrcingDetails(Long planId);
	List<ServiceData> retrieveServiceCodeDetails(Long planCode);
	PricingData retrieveSinglePriceDetails(String priceId);
}
