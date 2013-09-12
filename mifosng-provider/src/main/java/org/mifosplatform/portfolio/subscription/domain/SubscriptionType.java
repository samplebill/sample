package org.mifosplatform.portfolio.subscription.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_subscription_period")
public class SubscriptionType extends AbstractPersistable<Long> {

	@Column(name = "subscription_type", length = 100)
	private String subscription_type;

	@Column(name = "day_name")
	private String day_name;

	public SubscriptionType() {
	}

	public SubscriptionType(final String subscription_type,
			final String day_name) {

		this.subscription_type = subscription_type;
		this.day_name = day_name;

	}

	public String getDay_name() {
		return day_name;
	}

	public String getSubscription_type() {
		return subscription_type;
	}

}
