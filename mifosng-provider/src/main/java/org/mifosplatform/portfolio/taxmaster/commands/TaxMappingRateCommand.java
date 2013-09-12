package org.mifosplatform.portfolio.taxmaster.commands;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class TaxMappingRateCommand {
	private String chargeCode;
	private String taxCode;
	private LocalDate startdate;
	private String type;
	private BigDecimal value;
	public TaxMappingRateCommand(String chargeCode,String taxCode,LocalDate startdate,String type,BigDecimal value)
	{
		this.chargeCode=chargeCode;
		this.taxCode=taxCode;
		this.startdate=startdate;
		this.type=type;
		this.value=value;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public LocalDate getStartdate() {
		return startdate;
	}
	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
