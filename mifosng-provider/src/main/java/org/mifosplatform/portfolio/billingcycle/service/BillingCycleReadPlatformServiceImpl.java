package org.mifosplatform.portfolio.billingcycle.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.billingcycle.data.BillingCycleData;

import org.mifosplatform.portfolio.billingcycle.domain.FrequenctType;
import org.mifosplatform.portfolio.billingcycle.domain.SavingBillingEnumaration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BillingCycleReadPlatformServiceImpl implements BillingCycleReadPlatformService {


	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public BillingCycleData retrieveNewBillingCycleData() {

		   EnumOptionData sunday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.SUNDAY);
			EnumOptionData monday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.MONDAY);
			EnumOptionData tuesday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.TUESDAY);
			EnumOptionData wednesday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.WEDNESDAY);
			EnumOptionData thursday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.THURSDAY);
			EnumOptionData friday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.FRIDAY);
			EnumOptionData saturday = SavingBillingEnumaration.interestCompoundingPeriodType(FrequenctType.SATURDAY);
			List<EnumOptionData> frequencyType = Arrays.asList(sunday,monday,tuesday,wednesday,thursday,friday,saturday);


			List<BillingDays> list=new ArrayList<BillingDays>();


			for(int i=1;i<=30;i++)
			{
				list.add(new BillingDays(i));
			}

			return  new BillingCycleData(frequencyType,list);

	}





}
