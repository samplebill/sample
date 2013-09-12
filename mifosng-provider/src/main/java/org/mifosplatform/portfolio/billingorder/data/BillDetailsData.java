package org.mifosplatform.portfolio.billingorder.data;

import org.joda.time.LocalDate;


public class BillDetailsData  {
private Long id;
private Long clientId;
private String addrNo;
private String clientName;
private String billPeriod;
private String street;
private String zip;
private String city;
private String state;
private String country;
private Double previousBal;
private Double chargeAmount;
private Double adjustmentAmount;
private Double taxAmount;
private Double paidAmount;
private Double dueAmount;
private LocalDate billDate;
private LocalDate dueDate;
private String message;

	public BillDetailsData(Long id, Long clientId, String addrNo, String clientName,
			String billPeriod, String street, String zip, String city,
			String state, String country, Double previousBal,
			Double chargeAmount, Double adjustmentAmount, Double taxAmount,
			Double paidAmount, Double dueAmount,LocalDate billDate,LocalDate duDate,String message) {

		this.id=id;
		this.addrNo=addrNo;
		this.adjustmentAmount=adjustmentAmount;
		this.billPeriod=billPeriod;
		this.chargeAmount=chargeAmount;
		this.city=city;
		this.clientId=clientId;
		this.clientName=clientName;
		this.country=country;
		this.dueAmount=dueAmount;
		this.paidAmount=paidAmount;
		this.previousBal=previousBal;
		this.state=state;
		this.street=street;
		this.taxAmount=taxAmount;
		this.zip=zip;
		this.billDate=billDate;
		this.dueDate=duDate;
		this.message=message;

	}

	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getAddrNo() {
		return addrNo;
	}

	public String getClientName() {
		return clientName;
	}

	public String getBillPeriod() {
		return billPeriod;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public Double getPreviousBal() {
		return previousBal;
	}

	public Double getChargeAmount() {
		return chargeAmount;
	}

	public Double getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getMessage() {
		return message;
	}


}
