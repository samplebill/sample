package org.mifosplatform.infrastructure.configuration.data;

public class PeriodData {


	private final String subscription_type;
	private final Long id;
	private final String day_name;

	public PeriodData(final String subscription_type,final Long id,final String day_name)
	{
		this.subscription_type=subscription_type;
		this.id=id;
		this.day_name=day_name;
	}

	public String getDay_name() {
		return day_name;
	}

	public String getSubscription_type() {
		return subscription_type;
	}

	public Long getId() {
		return id;
	}


}
