package org.mifosplatform.portfolio.paymodes.data;

import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;

public class PaymodeData {

	private Long id;
	private String paymodeCode;
	private String paymodeDescription;
	private LocalDate startDate;
 private Collection<PaymodeData> paymodeDatas;
	
	public PaymodeData(Long id, String paymodeCode, String description) {
	this.id=id;
	this.paymodeCode=paymodeCode;
	this.paymodeDescription=description;
	}


	public PaymodeData(Collection<PaymodeData> data) {
		this.paymodeDatas=data;
		this.startDate=new LocalDate();
	
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
