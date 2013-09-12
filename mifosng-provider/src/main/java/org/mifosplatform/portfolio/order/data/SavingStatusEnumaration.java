package org.mifosplatform.portfolio.order.data;



import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.plan.domain.StatusTypeEnum;

public class SavingStatusEnumaration {

	public static EnumOptionData interestCompoundingPeriodType(final int id) {
		return interestCompoundingPeriodType(StatusTypeEnum.fromInt(id));
	}

	public static EnumOptionData interestCompoundingPeriodType(final StatusTypeEnum type) {
		final String codePrefix = "deposit.interest.compounding.period.";
		EnumOptionData optionData = null;
		switch (type) {
		case ACTIVE:
			optionData = new EnumOptionData(StatusTypeEnum.ACTIVE.getValue().longValue(), codePrefix + StatusTypeEnum.ACTIVE.getCode(), "ACTIVE");
			break;
		case INACTIVE:
			optionData = new EnumOptionData(StatusTypeEnum.INACTIVE.getValue().longValue(), codePrefix + StatusTypeEnum.INACTIVE.getCode(), "INACTIVE");
			break;

		case CANCELLED:
			optionData = new EnumOptionData(StatusTypeEnum.CANCELLED.getValue().longValue(), codePrefix + StatusTypeEnum.CANCELLED.getCode(), "CANCELLED");
			break;

		default:
			optionData = new EnumOptionData(StatusTypeEnum.INVALID.getValue().longValue(), StatusTypeEnum.INVALID.getCode(), "INVALID");
			break;
		}
		return optionData;
	}

}
