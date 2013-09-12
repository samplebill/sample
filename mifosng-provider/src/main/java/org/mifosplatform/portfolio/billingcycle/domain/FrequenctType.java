package org.mifosplatform.portfolio.billingcycle.domain;


public enum FrequenctType {

		SUNDAY(0, "FrequenctType.sunday"), //
		MONDAY(1, "FrequenctType.monday"), //
		TUESDAY(2, "FrequenctType.tuesday"),//
		WEDNESDAY(3,"FrequenctType.wednesday"),//
		THURSDAY(4, "FrequenctType.thursday"),
		FRIDAY(5, "FrequenctType.friday"),
		SATURDAY(6, "FrequenctType.saturday"),
		INVALID(7, "FrequenctType.invalid");

		    private final Integer value;
			private final String code;

		    private FrequenctType(final Integer value, final String code) {
		        this.value = value;
				this.code = code;
		    }

		    public Integer getValue() {
		        return this.value;
		    }

			public String getCode() {
				return code;
			}

			public static FrequenctType fromInt(final Integer frequency) {

				FrequenctType frequencyType = FrequenctType.INVALID;
				switch (frequency) {
				case 0:
					frequencyType = FrequenctType.SUNDAY;
					break;
				case 1:
					frequencyType = FrequenctType.MONDAY;
					break;
				case 2:
					frequencyType = FrequenctType.TUESDAY;
					break;
				case 3:
					frequencyType = FrequenctType.WEDNESDAY;
					break;
				case 4:
					frequencyType = FrequenctType.THURSDAY;
					break;
				case 5:
					frequencyType = FrequenctType.FRIDAY;
					break;
				case 6:
					frequencyType = FrequenctType.SATURDAY;
					break;

				default:
					frequencyType = FrequenctType.INVALID;
					break;
				}
				return frequencyType;
			}
		}
