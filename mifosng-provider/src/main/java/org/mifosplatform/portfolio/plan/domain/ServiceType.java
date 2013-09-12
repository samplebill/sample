package org.mifosplatform.portfolio.plan.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class ServiceType {

	@Id
	@GeneratedValue
	@Column(name ="id")
	private Long id;

	@Column(name = "service_code")
	private String code;

	@Column(name = "service_description")
	private String description;

	@Column(name = "type")
	private Long type;

	public ServiceType() {
		// TODO Auto-generated constructor stub
	}
	public ServiceType(final String code, final String description,
			final Long status) {
		this.code = code;
		this.description = description;
		this.type = status;

	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}


	public Long getId()
	{

		return id;
	}





}
