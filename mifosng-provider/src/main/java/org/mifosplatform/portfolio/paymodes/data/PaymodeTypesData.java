package org.mifosplatform.portfolio.paymodes.data;

public class PaymodeTypesData {

	private final Long id;
	private final String paymodeCode;
	private final String paymodeDescription;

	public PaymodeTypesData(Long id, String paymodeCode, String description) {
	this.id=id;
	this.paymodeCode=paymodeCode;
	this.paymodeDescription=description;


	}

	public Long getId() {
		return id;
	}

	public String getPaymodeCode() {
		return paymodeCode;
	}

	public String getPaymodeDescription() {
		return paymodeDescription;
	}




}
