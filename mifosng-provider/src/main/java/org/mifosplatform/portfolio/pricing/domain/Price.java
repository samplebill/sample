package org.mifosplatform.portfolio.pricing.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.portfolio.plan.commands.PlansCommand;
import org.mifosplatform.portfolio.pricing.commands.PricingCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "plan_pricing")
public class Price extends AbstractPersistable<Long> {

	@Column(name = "plan_code")
	private Long plan_code;

	@Column(name = "service_code")
	private String service_code;

	@Column(name = "charge_code")
	private String charge_code;

	@Column(name = "charging_variant")
	private String charging_variant;

	@Column(name = "price", scale = 6, precision = 19, nullable = false)
	private BigDecimal price;

	@Column(name = "discount_id", nullable = false)
	private Long discount_id;

	@Column(name = "is_deleted")
	private String is_deleted="n";

	public Price() {
	}

	public Price(final Long plan_code, final String charge_code,
			final String service_code, final String charging_variant,
			final BigDecimal price, final Long discount_id)

	{

		this.plan_code = plan_code;
		this.service_code = service_code;
		this.charge_code = charge_code;

		this.charging_variant = charging_variant;
		this.price = price;
		this.discount_id = discount_id;
	}

	public String getCharging_variant() {
		return charging_variant;
	}

	public void setCharging_variant(String charging_variant) {
		this.charging_variant = charging_variant;
	}

	public Long getPlanCode() {
		return plan_code;
	}

	public String getServiceCode() {
		return service_code;
	}

	public String getChargeCode() {
		return charge_code;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getDiscountId() {
		return discount_id;
	}

	public void update(PricingCommand command) {
		if (command.isServiceCodeChanged()) {
			this.service_code = command.getServiceCode();
		}
		if (command.isChargeCodeChanged()) {
			this.charge_code = command.getChargeCode();
		}
		if (command.isChargeVariantChanged()) {
			this.charging_variant = command.getChargingVariant();
			if (command.isDiscountChanged()) {
				this.discount_id = command.getDiscount_id();
			}
			if (command.isPriceChanged()) {
				this.price = command.getPrice();
			}
		}

	}

	public void delete() {



			this.is_deleted = "y";


	}
}
