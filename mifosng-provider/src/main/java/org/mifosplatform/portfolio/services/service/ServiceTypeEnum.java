package org.mifosplatform.portfolio.services.service;


public enum ServiceTypeEnum {

	GENERAL(0, "ServiceTypeEnum.general"), //
	  INVALID(1, "ServiceTypeEnum.invalid");


    private final Integer value;
	private final String code;

    private ServiceTypeEnum(final Integer value, final String code) {
        this.value = value;
		this.code = code;
    }

    public Integer getValue() {
        return this.value;
    }

	public String getCode() {
		return code;
	}

	public static ServiceTypeEnum fromInt(final Integer frequency) {

		ServiceTypeEnum repaymentFrequencyType = ServiceTypeEnum.INVALID;
		switch (frequency) {
		case 0:
			repaymentFrequencyType = ServiceTypeEnum.GENERAL;
			break;


		default:
			repaymentFrequencyType = ServiceTypeEnum.INVALID;
			break;
		}
		return repaymentFrequencyType;
	}

}
