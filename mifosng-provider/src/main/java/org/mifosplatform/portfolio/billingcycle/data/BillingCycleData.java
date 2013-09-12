package org.mifosplatform.portfolio.billingcycle.data;

import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;

public class BillingCycleData {

	private final List<EnumOptionData> categoryType;

	@SuppressWarnings("rawtypes")
	private final List days;



	public BillingCycleData(List<EnumOptionData> frequencyType, List list) {
		this.categoryType=frequencyType;
		this.days=list;
	}



	public List<EnumOptionData> getCategoryType() {
		return categoryType;
	}




	public List getDays() {
		return days;
	}


}
