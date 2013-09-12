package org.mifosplatform.portfolio.billingcycle.domain;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;

public class SavingBillingEnumaration {




		public static EnumOptionData interestCompoundingPeriodType(final int id) {
			return interestCompoundingPeriodType(FrequenctType.fromInt(id));
		}

		public static EnumOptionData interestCompoundingPeriodType(final FrequenctType type) {
			final String codePrefix = "deposit.interest.compounding.period.";
			EnumOptionData optionData = null;
			switch (type) {
			case SUNDAY:
				optionData = new EnumOptionData(FrequenctType.SUNDAY.getValue().longValue(), codePrefix + FrequenctType.SUNDAY.getCode(), "sunday");
				break;
			case MONDAY:
				optionData = new EnumOptionData(FrequenctType.MONDAY.getValue().longValue(), codePrefix + FrequenctType.MONDAY.getCode(), "monday");
				break;
			case TUESDAY:
				optionData = new EnumOptionData(FrequenctType.TUESDAY.getValue().longValue(), codePrefix + FrequenctType.TUESDAY.getCode(), "tuesday");
				break;
			case WEDNESDAY:
				optionData = new EnumOptionData(FrequenctType.WEDNESDAY.getValue().longValue(), codePrefix + FrequenctType.WEDNESDAY.getCode(), "wednesday");
				break;
			case THURSDAY:
				optionData = new EnumOptionData(FrequenctType.THURSDAY.getValue().longValue(), codePrefix + FrequenctType.THURSDAY.getCode(), "thursday");
				break;
			case FRIDAY:
				optionData = new EnumOptionData(FrequenctType.FRIDAY.getValue().longValue(), codePrefix + FrequenctType.FRIDAY.getCode(), "friday");
				break;
			case SATURDAY:
				optionData = new EnumOptionData(FrequenctType.SATURDAY.getValue().longValue(), codePrefix + FrequenctType.SATURDAY.getCode(), "saturday");
				break;
			default:
				optionData = new EnumOptionData(FrequenctType.INVALID.getValue().longValue(), FrequenctType.INVALID.getCode(), "Invalid");
				break;
			}
			return optionData;
		}
	}
