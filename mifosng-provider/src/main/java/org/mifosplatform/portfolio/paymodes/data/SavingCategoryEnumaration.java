package org.mifosplatform.portfolio.paymodes.data;

	import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.loanproduct.domain.PeriodFrequencyType;
import org.mifosplatform.portfolio.paymodes.domain.CategoryType;

	public class SavingCategoryEnumaration {

		public static EnumOptionData interestCompoundingPeriodType(final int id) {
			return interestCompoundingPeriodType(CategoryType.fromInt(id));
		}

		public static EnumOptionData interestCompoundingPeriodType(final CategoryType type) {
			final String codePrefix = "deposit.interest.compounding.period.";
			EnumOptionData optionData = null;
			switch (type) {
			case DIRECT_DEBIT:
				optionData = new EnumOptionData(CategoryType.DIRECT_DEBIT.getValue().longValue(), codePrefix + CategoryType.DIRECT_DEBIT.getCode(), "DIRECT");
				break;
			case CASH:
				optionData = new EnumOptionData(CategoryType.CASH.getValue().longValue(), codePrefix + CategoryType.CASH.getCode(), "cash");
				break;
			case CREDIT_CARD:
				optionData = new EnumOptionData(CategoryType.CREDIT_CARD.getValue().longValue(), codePrefix + CategoryType.CREDIT_CARD.getCode(), "credit card");
				break;
			case CHEQUE:
				optionData = new EnumOptionData(CategoryType.CHEQUE.getValue().longValue(), codePrefix + CategoryType.CHEQUE.getCode(), "cheque");
				break;
			default:
				optionData = new EnumOptionData(CategoryType.INVALID.getValue().longValue(), PeriodFrequencyType.INVALID.getCode(), "Invalid");
				break;
			}
			return optionData;
		}
	}
