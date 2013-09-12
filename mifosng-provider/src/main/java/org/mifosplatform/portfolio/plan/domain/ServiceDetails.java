package org.mifosplatform.portfolio.plan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan_detail")
public class ServiceDetails {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@ManyToOne
    @JoinColumn(name="plan_id")
    private Plan plan;

	@Column(name ="service_code", length=50)
    private String service_code;


	@Column(name = "is_deleted", nullable = false)
	private char is_deleted = 'n';


	public ServiceDetails()
	{}
	public ServiceDetails(final String service_code)
	{

		this.service_code=service_code;
		//this.is_deleted=null;
		this.plan=null;

	}


	public String getService_code() {
		return service_code;
	}


	public char isIs_deleted() {
		return is_deleted;
	}



	public Plan getPlan() {
		return plan;
	}

	public void update(Plan plan1)
	{
		this.plan=plan1;
	}
	public void delete() {
		this.is_deleted='y';

	}



}