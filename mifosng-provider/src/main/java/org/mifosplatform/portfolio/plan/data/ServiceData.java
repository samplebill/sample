package org.mifosplatform.portfolio.plan.data;

import java.math.BigDecimal;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;

public class ServiceData {

	private final Long id;

	private final String serviceCode;
	private final String planDescription;
	private final String planCode;
	private final List<EnumOptionData> data;
	private final String discountCode;
	private final BigDecimal price;
	private final String chargeCode;
	private final String chargeVariant;
	private final String services;
	private Long serviceId;
	private Long planId;
	private String chargeDescription;
	private String serviceDescription;
	private String code;

	public ServiceData(final List<EnumOptionData> data) {
		this.id = null;
		this.discountCode = null;
		this.serviceCode = null;
		this.planDescription = null;
		this.planCode = null;
		this.data = data;
		this.price = null;
		this.chargeCode = null;
		this.chargeVariant = null;
		this.services = null;
	}

	public ServiceData(Long id, String planCode, String serviceCode,
			String planDescription, String chargeCode, String charging_variant,
			BigDecimal price) {

		this.id = id;
		this.discountCode = null;
		this.serviceCode = serviceCode;
		this.planDescription = planDescription;
		this.planCode = planCode;
		this.data = null;
		this.chargeCode = chargeCode;
		this.chargeVariant = charging_variant;
		this.price = price;
		this.services = null;

	}

	public ServiceData(Long id, Long planId, String planCode,
			String chargeCode, String serviceCode, String serviceDescription) {
		this.id = id;
		this.planId = planId;
		this.discountCode = null;
		this.serviceCode = serviceCode;
		this.planDescription = null;
		this.planCode = serviceDescription;
		this.data = null;
		this.chargeCode = chargeCode;
		this.chargeVariant = null;
		this.price = null;
		this.services = null;
		this.chargeDescription = serviceDescription;
		this.code=planCode;
	}

	public Long getId() {
		return id;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public String getServiceDescription() {
		return planDescription;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public String getPlanCode() {
		return planCode;
	}

	public String getServices() {
		return services;
	}

	public Long getPlanId() {
		return planId;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public List<EnumOptionData> getData() {
		return data;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public String getChargeVariant() {
		return chargeVariant;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public String getChargeDescription() {
		return chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getCode() {
		return code;
	}



}
