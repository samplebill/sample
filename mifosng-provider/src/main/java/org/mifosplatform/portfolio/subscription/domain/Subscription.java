package org.mifosplatform.portfolio.subscription.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.portfolio.subscription.commands.SubscriptionCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "contract_period")
public class Subscription extends AbstractPersistable<Long> {

	@Column(name = "contract_period", nullable = false)
	private String subscriptionPeriod;

	@Column(name = "is_deleted", nullable = false)
	private boolean deleted = false;

	@Column(name = "contract_type", length = 100)
	private String subscriptionType;

	@Column(name = "contract_duration")
	private Long units;

	public Subscription() {
	}

	public Subscription(final String subscriptionPeriod, final Long units,
			final String subscriptionType, final Long subscriptionTypeId) {

		this.subscriptionPeriod = subscriptionPeriod;
		this.subscriptionType = subscriptionType;
		this.units = units;

	}

	public String getSubscriptionPeriod() {
		return subscriptionPeriod;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public Long getUnits() {
		return units;
	}

	public void update(SubscriptionCommand command, String type) {

		if (command.issubscriptionPeriodChanged()) {
			this.subscriptionPeriod = command.getSubscription_period();
		}

		if (command.isUnitsChanged()) {
			this.units = command.getUnits();
		}

		if (command.isSubscriptionTypeIdChanged()) {

			this.subscriptionType = type;
		}
		if (command.isDayNameChanged()) {

		}

	}

	public void delete() {
		if (deleted) {

		} else {
			this.deleted = true;

		}
	}

}
