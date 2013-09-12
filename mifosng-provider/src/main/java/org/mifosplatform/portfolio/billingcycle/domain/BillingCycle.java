package org.mifosplatform.portfolio.billingcycle.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_billing_cycle")
public class BillingCycle extends AbstractPersistable<Long> {


	@Column(name = "billing_code")
	private String billing_code;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "frequency")
	private String frequency;

	@Column(name = "every")
	private String every;





	public BillingCycle() {
	}

	public BillingCycle( final String billing_code,final String description,
			final String every,
			final String frequency
			) {

		this.billing_code = billing_code;
		this.description = description;
		this.frequency = frequency;
		this.every = every;


	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getEvery() {
		return every;
	}

	public void setEvery(String every) {
		this.every = every;
	}

	public String getBilling_code() {
		return billing_code;
	}

	public void setBilling_code(String billing_code) {
		this.billing_code = billing_code;
	}






}
