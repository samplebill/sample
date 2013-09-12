package org.mifosplatform.portfolio.services.data;

public class ServicesData {

	private final Long id;
	private final String planCode;
	private final String service_code;
	private final String service_description;
	public ServicesData(final Long id,final String planCode,final String service_code,final String service_desc)
		{
		this.id=id;
		this.planCode=planCode;
		this.service_code=service_code;
		this.service_description=service_desc;

	}
	public Long getId() {
		return id;
	}
	public String getPlanCode() {
		return planCode;
	}
	public String getService_code() {
		return service_code;
	}
	public String getService_description() {
		return service_description;
	}


}
