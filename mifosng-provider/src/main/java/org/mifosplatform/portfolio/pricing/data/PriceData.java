package org.mifosplatform.portfolio.pricing.data;

import java.math.BigDecimal;

public class PriceData {

	private Long id;
	private String chargeCode;
	private String serviceCode;
	private String charging_variant;
	private BigDecimal price;
	private String chagreType;
	private String chargeDuration;
	private String durationType;
	private Long serviceId;
	public PriceData(final Long id,final String serviceCode,final String chargeCode,
			final String charging_variant,final BigDecimal price,final String chrgeType,final String chargeDuration,final String durationType,final Long serviceId)
	{

		this.id=id;
		this.chargeCode=chargeCode;
		this.serviceCode=serviceCode;
		this.charging_variant=charging_variant;
		this.price=price;
		this.chagreType=chrgeType;
		this.chargeDuration=chargeDuration;
		this.durationType=durationType;
		this.serviceId=serviceId;
	}
	public Long getId() {
		return id;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public String getCharging_variant() {
		return charging_variant;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getChagreType() {
		return chagreType;
	}
	public String getChargeDuration() {
		return chargeDuration;
	}
	public String getDurationType() {
		return durationType;
	}
	public Long getServiceId() {
		return serviceId;
	}

}
