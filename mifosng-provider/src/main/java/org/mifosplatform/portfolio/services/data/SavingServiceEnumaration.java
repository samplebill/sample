package org.mifosplatform.portfolio.services.data;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.services.service.ServiceTypeEnum;

public class SavingServiceEnumaration {

	public static EnumOptionData interestCompoundingPeriodType(final int id) {
		return interestCompoundingPeriodType(ServiceTypeEnum.fromInt(id));
	}

	public static EnumOptionData interestCompoundingPeriodType(final ServiceTypeEnum type) {
		final String codePrefix = "deposit.interest.compounding.period.";
		EnumOptionData optionData = null;
		switch (type) {
		case GENERAL:
			optionData = new EnumOptionData(ServiceTypeEnum.GENERAL.getValue().longValue(), codePrefix + ServiceTypeEnum.GENERAL.getCode(), "GENERAL");
			break;

		default:
			optionData = new EnumOptionData(ServiceTypeEnum.INVALID.getValue().longValue(), ServiceTypeEnum.INVALID.getCode(), "Invalid");
			break;
		}
		return optionData;
	}

}
