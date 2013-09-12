package org.mifosplatform.portfolio.billingcycle.data;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.plan.domain.BillingTypeEnum;




public class SavingBillingEnumaration {

	public static EnumOptionData interestCompoundingPeriodType(final int id) {
		return interestCompoundingPeriodType(BillingTypeEnum.fromInt(id));
	}

	public static EnumOptionData interestCompoundingPeriodType(final BillingTypeEnum type) {
		final String codePrefix = "deposit.interest.compounding.period.";
		EnumOptionData optionData = null;
		switch (type) {
		case EXACT:
			optionData = new EnumOptionData(BillingTypeEnum.EXACT.getValue().longValue(), codePrefix + BillingTypeEnum.EXACT.getCode(), "EXACT");
			break;
		case FULL:
			optionData = new EnumOptionData(BillingTypeEnum.FULL.getValue().longValue(), codePrefix + BillingTypeEnum.FULL.getCode(), "FULL");
			break;
		case CUSTOM:
			optionData = new EnumOptionData(BillingTypeEnum.CUSTOM.getValue().longValue(), codePrefix + BillingTypeEnum.CUSTOM.getCode(), "CUSTOM");
			break;

		default:
			optionData = new EnumOptionData(BillingTypeEnum.INVALID.getValue().longValue(), BillingTypeEnum.INVALID.getCode(), "INVALID");
			break;
		}
		return optionData;
	}

}
