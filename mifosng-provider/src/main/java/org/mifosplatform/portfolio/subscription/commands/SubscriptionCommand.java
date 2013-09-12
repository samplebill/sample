package org.mifosplatform.portfolio.subscription.commands;

import java.util.Set;

public class SubscriptionCommand {

	private final String subscription_period;

	//private final String subscription_type;
	private final Long subscriptionTypeId;
	private final Long units;
	private String day_name;

	private final Long id;
	private final Set<String> modifiedParameters;

	public SubscriptionCommand(final Set<String> modifiedParameters,
			final Long subScriptionId, final Long id,
			final String subscription_period2,
			final Long units, String day_name, final Long subscriptionTypeId) {
		this.subscription_period = subscription_period2;
	//	this.subscription_type = subscription_type;
		this.units = units;
		this.id = id;
		this.day_name = day_name;//subscription_type.equalsIgnoreCase("WEEKLY") ? day_name
			//	: null;
		this.subscriptionTypeId = subscriptionTypeId;
		this.modifiedParameters = modifiedParameters;
	}



	public Long getSubscriptionTypeId() {
		return subscriptionTypeId;
	}

	public String getSubscription_period() {
		return subscription_period;
	}
//	public String getSubscription_type() {
//		return subscription_type;
//	}

	public Long getUnits() {
		return units;
	}

	public String getDay_name() {

		return day_name;

	}

	public Long getId() {
		return id;
	}

	public boolean issubscriptionPeriodChanged() {
		return this.modifiedParameters.contains("subscription_period");
	}

	public boolean issubscriptionTypeChanged() {
		return this.modifiedParameters.contains("subscription_type");
	}

	public boolean isUnitsChanged() {
		return this.modifiedParameters.contains("units");
	}

	public boolean isDayNameChanged() {
		return this.modifiedParameters.contains("day_name");
	}

	public boolean isSubscriptionTypeIdChanged() {
		return this.modifiedParameters.contains("subscriptionTypeId");
	}

}
