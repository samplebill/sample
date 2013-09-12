package org.mifosplatform.portfolio.plan.domain;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDate;
import org.mifosplatform.portfolio.plan.commands.PlansCommand;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;

@Entity
@Table(name = "plan_master")
public class Plan{


	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name = "plan_code", length = 65536)
	private String code;

	@Column(name = "plan_description")
	private String description;

	@Column(name = "plan_status")
	private Long status;
	@Column(name = "contract_period")
	private String contract_period;

	@Column(name = "start_date")
	private Date start_date;

	@Column(name = "end_date")
	private Date end_date;

	@Column(name = "bill_rule")
	private Long bill_rule;

	@Column(name = "is_deleted", nullable = false)
	private char deleted='n';

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plan", orphanRemoval = true)
	private List<ServiceDetails> details = new ArrayList<ServiceDetails>();

	public Plan() {
		// TODO Auto-generated constructor stub
	}

	public Plan(final String code, final String description,
			final LocalDate start_date, final LocalDate endDate,
			final Long bill_rule, final Long status,
			 final String contractPeriod,
			final List<ServiceDetails> details) {
		this.code = code;
		this.description = description;
		if (endDate != null)
			this.end_date = endDate.toDate();
		this.start_date = start_date.toDate();
		this.status = status;
		this.contract_period = contractPeriod;
		this.bill_rule = bill_rule;
		this.details = details;

	}

	public List<ServiceDetails> getDetails() {
		return details;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Long getStatus() {
		return status;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}



	public String getContractPeriod() {
		return contract_period;
	}

	public void addServieDetails(ServiceDetails serviceDetail) {

		serviceDetail.update(this);
		this.details.add(serviceDetail);

	}

	public Long getBill_rule() {
		return bill_rule;
	}

	public void update(PlansCommand command, PlanData serviceData,
			List<ServiceData> services) {

		this.code = command.isplanCodeChanged() ? command.getPlan_code()
				: this.code;

		this.description = command.isplanDescriptionChanged() ? command
				.getPlan_description() :  this.description;

		this.start_date = command.isStartDateChanged() ? command
				.getStartDate().toDate() :this.start_date;
		if (command.getEndDate() == null) {
			this.end_date = null;

		} else {
			this.end_date = command.isendDateChanged() ? command.getEndDate()
					.toDate() : this.end_date;
		}
		this.bill_rule = command.isBillingRuleChanged() ? command.getBillRule()
				: this.bill_rule;
		this.contract_period = command.isContractPeriodChanged() ? command
				.getContractPeriod() : this.contract_period;

		this.status = command.isPlanStatusChanged() ? command.getStatus()
				:this.status;

	}

	public void delete(PlanData data) {

		/*this.code = data.getPlan_code();
		this.description = data.getPlan_description();

		if (data.getEndDate() != null)
			this.end_date = data.getEndDate().toDate();
		this.start_date = data.getStartDate().toDate();
		this.status = data.getStatus();
		this.contract_period = data.getPeriod();
		this.bill_rule = data.getBillRule();*/

		if (deleted =='y') {

		} else {
			this.deleted = 'y';
			this.description = this.getCode() + "_DELETED_";

		}
	}

}
