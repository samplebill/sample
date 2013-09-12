package org.mifosplatform.portfolio.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.LocalDate;

@Entity
@Table(name = "order_price")
public class OrderPrice {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "service_id")
	private Long serviceId;

	@Column(name = "charge_code")
	private String chargeCode;

	@Column(name = "charge_type")
	private String chargeType;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "charge_duration")
	private String chargeDuration;

	@Column(name = "duration_type")
	private String durationType;

	@Column(name = "invoice_tilldate")
	private Date invoiceTillDate;

	@Column(name = "bill_start_date")
	private Date billStartDate;
	
	@Column(name = "next_billable_day")
	private Date nextBillableDay;

	@Column(name = "bill_end_date")
	private Date billEndDate;

	@Column(name = "is_deleted")
	private char isDeleted;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", insertable = true, updatable = true, nullable = true, unique = true)
	private Order orders;

	public OrderPrice(final Long serviceId, final String chargeCode,
			final String chargeType, final BigDecimal price,
			final Date invoiceTillDate, final String chargetype,
			final String chargeduration, final String durationType,
			final Date billStartDate,final Date billEndDate) {

		this.orders = null;
		this.serviceId = serviceId;
		this.chargeCode = chargeCode;
		this.chargeType = chargetype;
		this.chargeDuration = chargeduration;
		this.durationType = durationType;
		this.price = price;
		this.invoiceTillDate = invoiceTillDate;
		this.billStartDate=billStartDate;
		this.billEndDate=billEndDate;


	}

	public OrderPrice() {
		// TODO Auto-generated constructor stub
	}

	public OrderPrice(Long id2, Order order, Long serviceId, String chargeCode,
			String chargeType, String chargeDuration, String durationType,
			Date invoiceTillDate, BigDecimal price, Long createdBy,
			Date createdDate, Date lastModifiedDate, Long lastModifiedBy) {

		this.orders = order;
		this.serviceId = serviceId;
		this.chargeCode = chargeCode;
		this.chargeType = chargeType;
		this.chargeDuration = chargeDuration;
		this.durationType = durationType;
		this.price = price;
		this.invoiceTillDate = invoiceTillDate;

	}

	public OrderPrice(Long id, Long orderId, Long serviceId,
			String chargeCode, String chargeType, String chargeDuration,
			String durationType, Date invoiceTillDate, BigDecimal price,
			Long createdBy, Date createdDate, Date lastModifiedDate,
			Long lastModifiedBy) {

		this.orders = null;
		this.serviceId = serviceId;
		this.chargeCode = chargeCode;
		this.chargeType = chargeType;
		this.chargeDuration = chargeDuration;
		this.durationType = durationType;
		this.price = price;
		this.invoiceTillDate = invoiceTillDate;




	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Transient
	private Long orderId;

	public Long getServiceId() {
		return serviceId;
	}
	
	

	public void updateDates(LocalDate date) {
		this.billEndDate =date.toDate();
		//this.nextBillableDay=date.plusDays(1).toDate();
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public String getChargeType() {
		return chargeType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getChargeDuration() {
		return chargeDuration;
	}

	public String getDurationType() {
		return durationType;
	}

	public Date getInvoiceTillDate() {
		return invoiceTillDate;
	}

	public void setInvoiceTillDate(Date invoiceTillDate) {
		this.invoiceTillDate = invoiceTillDate;
	}

	public char isIsDeleted() {
		return isDeleted;
	}

	public Order getOrder() {
		return orders;
	}

	public void update(Order order) {
		this.orders = order;

	}

	public void delete() {
		this.isDeleted = 'y';

	}

	public Long getId() {
		return id;
	}

	public void setChargeDuration(String chargeDuration) {
		this.chargeDuration = chargeDuration;
	}

	public Date getBillStartDate() {
		return billStartDate;
	}

	public Date getBillEndDate() {
		return billEndDate;
	}

	public Order getOrders() {
		return orders;
	}

	public void setNextBillableDay(Date nextBillableDate) {
		 this.nextBillableDay=nextBillableDate;
		
	}


}
