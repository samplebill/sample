package org.mifosplatform.portfolio.order.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.order.command.OrdersCommand;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "plan_id")
	private Long plan_id;

	@Column(name = "order_status")
	private Long status;

	@Column(name = "transaction_type")
	private String transaction_type;

	@Column(name = "billing_frequency")
	private String billing_frequency;

	@Column(name = "next_billable_day")
	private Date next_billable_day;

	@Column(name = "start_date")
	private Date start_date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date end_date;

	@Column(name = "contract_period")
	private Long contarctPeriod;

	@Column(name = "is_deleted")
	private char is_deleted;

	@Column(name = "billing_align")
	private char billingAlign;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", orphanRemoval = true)
	private List<OrderLine> services = new ArrayList<OrderLine>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", orphanRemoval = true)
	private List<OrderPrice> price = new ArrayList<OrderPrice>();

	public Order() {
	}

	public Order(Long client_id, Long plan_id, Long status, Long duration_type,String billingFreq,
			LocalDate startDate, LocalDate endDate, Long contract,
			List<OrderLine> serviceDetails, List<OrderPrice> orderprice,char billalign) {
		this.clientId = client_id;
		this.plan_id = plan_id;
		this.status = status;
		this.transaction_type = "Add Order";
		this.billing_frequency =billingFreq;
		this.start_date = startDate.toDate();
		if (endDate != null)
			this.end_date = endDate.toDate();
		this.services = serviceDetails;
		this.price = orderprice;
		this.contarctPeriod = contract;
		this.billingAlign=billalign;
		this.is_deleted='n';
	}

	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public Long getPlan_id() {
		return plan_id;
	}

	public Long getStatus() {
		return status;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public String getBilling_frequency() {
		return billing_frequency;
	}


	public Date getNext_billable_day() {
		return next_billable_day;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public List<OrderLine> getServices() {
		return services;
	}

	public List<OrderPrice> getPrice() {
		return price;
	}

	public void addServiceDeatils(OrderLine orderDetail) {
		orderDetail.update(this);
		this.services.add(orderDetail);

	}

	public void addOrderDeatils(OrderPrice price) {
		price.update(this);
		this.price.add(price);

	}

	public Long getContarctPeriod() {
		return contarctPeriod;
	}

	public void delete() {
		this.is_deleted = 'y';

	}

	public void update(LocalDate currentDate) {

		if (this.status != 3) {
			this.end_date = currentDate.toDate();

			this.status = new Integer(3).longValue();
		}

	}

}
