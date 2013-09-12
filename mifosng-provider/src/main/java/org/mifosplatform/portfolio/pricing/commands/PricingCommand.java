package org.mifosplatform.portfolio.pricing.commands;

import java.math.BigDecimal;
import java.util.Set;

public class PricingCommand {

	private final  String plan_code;
	private final String service_code;
	private final String charge_code;
	private final  String charging_variant;

	private final  BigDecimal price;
	private final Long discount_id;


	private final Set<String> modifiedParameters;

	public PricingCommand(Set<String> modifiedParameters,
		 String plan_code, String service_code,
			String charge_code, String charging_variant, BigDecimal price,
			Long discount_id) {
		this.charge_code=charge_code;
		this.service_code=service_code;
		this.plan_code=plan_code;
		this.charging_variant=charging_variant;
		this.price=price;
		this.discount_id=discount_id;
		this.modifiedParameters=modifiedParameters;


	}

	public String getPlanCode() {
		return plan_code;
	}

	public String getServiceCode() {
		return service_code;
	}

	public String getChargeCode() {
		return charge_code;
	}

	public String getChargingVariant() {
		return charging_variant;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getDiscount_id() {
		return discount_id;
	}

	public Set<String> getModifiedParameters() {
		return modifiedParameters;
	}

	public boolean isServiceCodeChanged() {
		return this.modifiedParameters.contains("service_code");
	}

	public boolean isChargeCodeChanged() {
		return this.modifiedParameters.contains("charge_code");
	}
	public boolean isChargeVariantChanged() {
		return this.modifiedParameters.contains("chargevariant");
	}
	public boolean isPriceChanged() {
		return this.modifiedParameters.contains("price");
	}

	public boolean isDiscountChanged() {
		return this.modifiedParameters.contains("discount_id");
	}



}
