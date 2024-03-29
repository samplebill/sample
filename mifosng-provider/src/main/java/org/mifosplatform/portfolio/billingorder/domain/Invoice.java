package org.mifosplatform.portfolio.billingorder.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name="client_id")
	private Long clientId;

	@Column(name="invoice_date")
	private Date invoiceDate;

	@Column(name="invoice_amount")
	private BigDecimal invoiceAmount;

	@Column(name="netcharge_amount")
	private BigDecimal netChargeAmount;

	@Column(name="tax_amount")
	private BigDecimal taxAmount;

	@Column(name="invoice_status")
	private String invoiceStatus;

	@Column(name="createdby_id")
	private Long createdBy;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="lastmodified_date")
	private Date lastModifiedDate;

	@Column(name="lastmodifiedby_id")
	private Long lastModifedById;

	private Invoice(){

	}

	public Invoice(final Long clientId,final Date invoiceDate,
			final BigDecimal invoiceAmount,final BigDecimal netChargeAmount,
			final BigDecimal taxAmount,final String invoiceStatus,final Long createdBy,
			final Date createdDate,final Date lastModifiedDate,final Long lastModifedById) {

		this.clientId = clientId;
		this.invoiceDate = invoiceDate;
		this.invoiceAmount = invoiceAmount;
		this.netChargeAmount = netChargeAmount;
		this.taxAmount = taxAmount;
		this.invoiceStatus = invoiceStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifedById = lastModifedById;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public BigDecimal getNetChargeAmount() {
		return netChargeAmount;
	}

	public void setNetChargeAmount(BigDecimal netChargeAmount) {
		this.netChargeAmount = netChargeAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getLastModifedById() {
		return lastModifedById;
	}

	public void setLastModifedById(Long lastModifedById) {
		this.lastModifedById = lastModifedById;
	}




}
