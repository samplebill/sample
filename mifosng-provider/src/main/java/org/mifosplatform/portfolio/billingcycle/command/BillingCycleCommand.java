package org.mifosplatform.portfolio.billingcycle.command;

import java.util.Set;

public class BillingCycleCommand {

	private final String billing_code;
	private final String description;
	private final String frequency;

	private final String[] every;

	private final Set<String> modifiedParameters;

	public BillingCycleCommand(final Set<String> modifiedParameters,
			final String billing_code,
			final String description,
			final  String frequency, final String[] every) {
		this.billing_code = billing_code;
		this.description = description;
		this.frequency = frequency;
		this.every = every;

		this.modifiedParameters = modifiedParameters;
	}



	public String getBilling_code() {
		return billing_code;
	}

	public String getDescription() {
		return description;
	}

	public String getFrequency() {
		return frequency;
	}

	public String[] getEvery() {
		return every;
	}

	public Set<String> getModifiedParameters() {
		return modifiedParameters;
	}


}
