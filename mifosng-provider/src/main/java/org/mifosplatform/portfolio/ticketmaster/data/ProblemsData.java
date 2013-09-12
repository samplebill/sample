package org.mifosplatform.portfolio.ticketmaster.data;

public class ProblemsData {
	
	private final Long id;
	private final String problemCode;
	private final String problemDescription;
	
	public ProblemsData(final Long id,final String problemCode,final String problemDesc){
		this.id=id;
		this.problemCode=problemCode;
		this.problemDescription=problemDesc;
	}

	public Long getId() {
		return id;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public String getProblemDescription() {
		return problemDescription;
	}
	
	

}
