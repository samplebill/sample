package org.mifosplatform.portfolio.order.command;

import java.math.BigDecimal;
import java.util.Set;

import org.joda.time.LocalDate;

public class OrdersCommand {

	private final Long planid;
	private final String billingFrequency;
	private final Long clientId;

	private final Long serviceStatus;
	private final Long chargeType;

	private final LocalDate startDate;

	private String chargeDuration;
	private Long durationType;
	private Long contractPeriod;
	private final String[] serviceType;
	private final int[] serviceId;
	private final Long chargeCode;
	private final Set<String> modifiedParameters;
	private final BigDecimal price;
	private final boolean billAlign;

	public OrdersCommand(final Set<String> modifiedParameters,
			final Long plan_id, final Long client_id,
			final LocalDate start_date, final String paytermtype,
			final Long contractPeriod, Long clientId,boolean billalign) {
		this.billingFrequency = paytermtype;
		this.chargeCode = null;
		this.chargeDuration = null;
		this.chargeType = null;
		this.clientId = clientId;
		this.durationType = null;
		this.modifiedParameters = modifiedParameters;
		this.planid = plan_id;
		this.price = null;
		this.serviceId = null;
		this.serviceStatus = null;
		this.serviceType = null;
		this.startDate = start_date;
		this.contractPeriod = contractPeriod;
		this.billAlign=billalign;
	}

	public Long getPlanid() {
		return planid;
	}

	public String getBillingFrequency() {
		return billingFrequency;
	}

	public Long getClientId() {
		return clientId;
	}

	public Long getServiceStatus() {
		return serviceStatus;
	}

	public Long getChargeType() {
		return chargeType;
	}

	public String getChargeDuration() {
		return chargeDuration;
	}

	public Long getDurationType() {
		return durationType;
	}

	public Long getChargeCode() {
		return chargeCode;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public String[] getServiceType() {
		return serviceType;
	}

	public int[] getServiceId() {
		return serviceId;
	}

	public Set<String> getModifiedParameters() {
		return modifiedParameters;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getContractPeriod() {
		return contractPeriod;
	}

	public boolean isBillAlign() {
		return billAlign;
	}



}
