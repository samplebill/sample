package org.mifosplatform.portfolio.paymodes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.portfolio.paymodes.commands.PaymodeCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "paymodes")
public class Paymode extends AbstractPersistable<Long> {

	@Column(name = "paymode_code", nullable = false)
	private String paymodeCode;

	@Column(name = "paymode_description", length = 100)
	private String description;
	
	@Column(name = "is_deleted")
	private char isDeleted='n';
	
	public Paymode()
	{}

	public Paymode(final String paymodeCode, final String description
			) {

		this.paymodeCode = paymodeCode;
		this.description = description;

	}

	public String getPaymodeCode() {
		return paymodeCode;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void update(PaymodeCommand command) {
		if (command.ispaymodeCodeChanged()) {
			this.paymodeCode = command.getPaymode();
		}

		if (command.ispaymodeDescChanged()) {
			this.description = command.getDescription();
		}

		
		
	}

	public void delete() {
	if(this.isDeleted=='y'){
		
	}
	else{
	this.isDeleted='y';
	}
	}
		
	}


