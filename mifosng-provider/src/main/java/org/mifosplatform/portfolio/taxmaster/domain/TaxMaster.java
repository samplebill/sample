package org.mifosplatform.portfolio.taxmaster.domain;
import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "tax_master")
public class TaxMaster extends AbstractPersistable<Long> {
	@Column(name = "tax_code", nullable = false, length = 20)
	private String taxCode;

	@Column(name = "tax_type", nullable = false, length = 20)
	private String taxType;

	@Column(name = "tax_description", nullable = false, length = 20)
	private String taxDescription;


	public TaxMaster(String taxCode,String taxType,String taxDescription)
	{
		this.taxCode=taxCode;
		this.taxType=taxType;
		this.taxDescription=taxDescription;
	}
	public static TaxMaster create(String taxCode,String taxType,String taxDescription)
	{
		return new TaxMaster(taxCode,taxType,taxDescription);
	}
	public TaxMaster() {
		// TODO Auto-generated constructor stub
	}
}
